package org.example.util.serialization.json;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.example.model.ChestDevice;

public class JsonSerdes {

    public static Serde<ChestDevice> ChestDevice() {
        JsonSerializer<ChestDevice> serializer = new JsonSerializer<>();
        JsonDeserializer<ChestDevice> deserializer = new JsonDeserializer<>(ChestDevice.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
