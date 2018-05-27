package ch.bfh.ti.nussa2.bti7311.model;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;

import java.io.*;

public class SMNWeatherDataSchema implements DeserializationSchema<SMNWeatherData>, SerializationSchema<SMNWeatherData> {

    @Override
    public SMNWeatherData deserialize(byte[] message) throws IOException {
        ObjectInputStream in;
        ByteArrayInputStream byteArrayInputStream;

        SMNWeatherData result = null;

        byteArrayInputStream = new ByteArrayInputStream(message);
        in = new ObjectInputStream(byteArrayInputStream);

        try {
            result = (SMNWeatherData) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public byte[] serialize(SMNWeatherData element) {

        ObjectOutputStream out;
        ByteArrayOutputStream byteOutputStream = null;
        byte[] result = null;

        try {
            byteOutputStream = new ByteArrayOutputStream();
            out = new ObjectOutputStream(byteOutputStream);
            out.writeObject(element);
            out.flush();
            result = byteOutputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
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
