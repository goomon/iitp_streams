package org.example.topology;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.example.model.*;
import org.example.util.extractor.DeviceTimestampExtractor;
import org.example.util.serialization.json.JsonSerdes;

import java.time.Duration;

public class DeviceProcessingTopology {

    public static Topology build() {
        StreamsBuilder builder = new StreamsBuilder();

        // Source options.
        Consumed<String, ChestDevice> chestConsumerOptions =
                Consumed.with(Serdes.String(), JsonSerdes.ChestDevice())
                        .withTimestampExtractor(new DeviceTimestampExtractor());
        Consumed<String, WristDevice> wristConsumerOptions =
                Consumed.with(Serdes.String(), JsonSerdes.WristDevice())
                        .withTimestampExtractor(new DeviceTimestampExtractor());

        // Window options.
        TimeWindows chestWindow = TimeWindows.ofSizeAndGrace(Duration.ofSeconds(10), Duration.ofSeconds(5))
                .advanceBy(Duration.ofSeconds(5));
        TimeWindows wristWindow = TimeWindows.ofSizeAndGrace(Duration.ofSeconds(10), Duration.ofSeconds(5))
                .advanceBy(Duration.ofSeconds(5));

        // Chest device stream.
        KStream<String, ChestDevice> chestEvents = builder.stream("chest", chestConsumerOptions);
        KTable<Windowed<String>, ChestDevice> chestDeviceKTable =
                chestEvents
                        .groupByKey()
                        .windowedBy(chestWindow)
                        .reduce(ChestDevice::combine)
                        .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded().shutDownWhenFull()));
        KStream<String, ChestDeviceFeature> chestDeviceFeatureKStream =
                chestDeviceKTable
                        .toStream()
                        .map((key, value) -> KeyValue.pair(key.key(), new ChestDeviceFeature(value)));

        // Wrist device stream.
        KStream<String, WristDevice> wristEvents = builder.stream("wrist", wristConsumerOptions);
        KTable<Windowed<String>, WristDevice> wristDeviceKTable =
                wristEvents
                        .groupByKey()
                        .windowedBy(wristWindow)
                        .reduce(WristDevice::combine)
                        .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded().shutDownWhenFull()));
        KStream<String, WristDeviceFeature> wristDeviceFeatureKStream =
                wristDeviceKTable
                        .toStream()
                        .map((key, value) -> KeyValue.pair(key.key(), new WristDeviceFeature(value)));

        // Stream join.
        StreamJoined<String, ChestDeviceFeature, WristDeviceFeature> joinParams =
                StreamJoined.with(Serdes.String(), JsonSerdes.ChestDeviceFeature(), JsonSerdes.WristDeviceFeature());
        JoinWindows joinWindows =
                JoinWindows.ofTimeDifferenceAndGrace(Duration.ofSeconds(3), Duration.ofSeconds(3));
        ValueJoiner<ChestDeviceFeature, WristDeviceFeature, CombinedDevice> valueJoiner = CombinedDevice::new;
        KStream<String, CombinedDevice> deviceJoined =
                chestDeviceFeatureKStream.join(wristDeviceFeatureKStream, valueJoiner, joinWindows, joinParams);

        // Transmit to sink down stream.
        Produced<String, CombinedDevice> producerOptions =
                Produced.with(Serdes.String(), JsonSerdes.CombinedDevice());
        deviceJoined.to("feature", producerOptions);

        // debug only.
        chestDeviceFeatureKStream
                .print(Printed.<String, ChestDeviceFeature>toSysOut().withLabel("debug-chest"));
        wristDeviceFeatureKStream
                .print(Printed.<String, WristDeviceFeature>toSysOut().withLabel("debug-wrist"));
        deviceJoined
                .print(Printed.<String, CombinedDevice>toSysOut().withLabel("debug-joined"));
        return builder.build();
    }
}
