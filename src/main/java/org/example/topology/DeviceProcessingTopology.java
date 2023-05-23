package org.example.topology;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.example.model.ChestDevice;
import org.example.model.ChestDeviceFeature;
import org.example.util.extractor.DeviceTimestampExtractor;
import org.example.util.serialization.json.JsonSerdes;

import java.time.Duration;

public class DeviceProcessingTopology {

    public static Topology build() {
        StreamsBuilder builder = new StreamsBuilder();
        Consumed<String, ChestDevice> chestConsumerOptions =
                Consumed.with(Serdes.String(), JsonSerdes.ChestDevice())
                        .withTimestampExtractor(new DeviceTimestampExtractor());
        TimeWindows hoppingWindow = TimeWindows.ofSizeWithNoGrace(Duration.ofSeconds(10))
                .advanceBy(Duration.ofSeconds(5));

        KStream<String, ChestDevice> chestEvents = builder.stream("chest", chestConsumerOptions);
        KTable<Windowed<String>, ChestDevice> chestDeviceKTable =
                chestEvents
                        .groupByKey()
                        .windowedBy(hoppingWindow)
                        .reduce(ChestDevice::combine)
                        .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded().shutDownWhenFull()));
        KStream<String, ChestDeviceFeature> chestDeviceFeatureKStream =
                chestDeviceKTable
                        .toStream()
                        .map((key, value) -> KeyValue.pair(key.key(), new ChestDeviceFeature(value)));

        // debug only.
        chestDeviceFeatureKStream
                .print(Printed.<String, ChestDeviceFeature>toSysOut().withLabel("debug"));
        return builder.build();
    }
}
