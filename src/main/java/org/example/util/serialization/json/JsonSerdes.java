package org.example.util.serialization.json;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.example.model.ChestDevice;
import org.example.model.WristDevice;

public class JsonSerdes {

    public static Serde<ChestDevice> ChestDevice() {
        JsonSerializer<ChestDevice> serializer = new JsonSerializer<>();
        JsonDeserializer<ChestDevice> deserializer = new JsonDeserializer<>(ChestDevice.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<WristDevice> WristDevice() {
        JsonSerializer<WristDevice> serializer = new JsonSerializer<>();
        JsonDeserializer<WristDevice> deserializer = new JsonDeserializer<>(WristDevice.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
