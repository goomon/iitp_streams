package org.example.util.serialization.json;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.example.model.ChestDevice;
import org.example.model.ChestDeviceFeature;
import org.example.model.WristDevice;
import org.example.model.WristDeviceFeature;

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

    public static Serde<ChestDeviceFeature> ChestDeviceFeature() {
        JsonSerializer<ChestDeviceFeature> serializer = new JsonSerializer<>();
        JsonDeserializer<ChestDeviceFeature> deserializer = new JsonDeserializer<>(ChestDeviceFeature.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<WristDeviceFeature> WristDeviceFeature() {
        JsonSerializer<WristDeviceFeature> serializer = new JsonSerializer<>();
        JsonDeserializer<WristDeviceFeature> deserializer = new JsonDeserializer<>(WristDeviceFeature.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
