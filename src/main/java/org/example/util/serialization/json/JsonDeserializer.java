package org.example.util.serialization.json;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Deserializer;

import java.lang.reflect.Type;
import java.util.Map;

public class JsonDeserializer<T> implements Deserializer<T> {
    private final Gson gson =
            new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
    private Class<T> destinationClass;
    private Type reflectionTypeToken;

    public JsonDeserializer(Class<T> destinationClass) {
        this.destinationClass = destinationClass;
    }

    public JsonDeserializer(Type reflectionTypeToken) {
        this.reflectionTypeToken = reflectionTypeToken;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        Type type = destinationClass != null ? destinationClass : reflectionTypeToken;
        return gson.fromJson(new String(data), type);
    }

    @Override
    public void close() {
    }
}
