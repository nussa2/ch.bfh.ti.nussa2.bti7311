package ch.bfh.ti.nussa2.bti7311.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;

import java.io.IOException;

public class SMNWeatherDataJSONSchema implements DeserializationSchema<SMNWeatherData>, SerializationSchema<SMNWeatherData> {


    private Gson createGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();

    }

    @Override
    public SMNWeatherData deserialize(byte[] message) throws IOException {

        SMNWeatherData result = createGson().fromJson(new String(message), SMNWeatherData.class);

        return result;
    }

    @Override
    public byte[] serialize(SMNWeatherData element) {

        String result = createGson().toJson(element, SMNWeatherData.class);

        return result.getBytes();
    }

    @Override
    public boolean isEndOfStream(SMNWeatherData nextElement) {
        return false;
    }

    @Override
    public TypeInformation<SMNWeatherData> getProducedType() {
        return TypeExtractor.getForClass(SMNWeatherData.class);
    }
}
