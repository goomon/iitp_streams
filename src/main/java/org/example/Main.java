package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.example.topology.DeviceProcessingTopology;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Topology topology = DeviceProcessingTopology.build();
        Properties props = getProps();
        KafkaStreams streams = new KafkaStreams(topology, props);
        streams.cleanUp();
        streams.start();
    }

    private static Properties getProps() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "dev-streams");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092");
        props.put(StreamsConfig.REPLICATION_FACTOR_CONFIG, 1);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }
}