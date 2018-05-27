package ch.bfh.ti.nussa2.bti7311.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SMNWeatherDataJsonSchemaTest {

    SMNWeatherData weatherData;

    SMNWeatherDataJSONSchema weatherDataSchema;

    String weatherDataJson = "{\"location\":\"test\",\"time\":\"2018-05-26T23:16:23.014+02:00\",\"airTemperature\":25.1,\"precipitation\":5.0,\"pressureSeaLevelQNH\":1008.0,\"gustPeak\":12.0,\"pressureSeaLevelQFF\":1012.0}";

    @Before
    public void setUp() throws Exception {
        weatherData = new SMNWeatherData();
        Date date = new Date();
        date.setTime(1527369383014L);
        weatherData.setTime(date);
        weatherData.setAirTemperature(25.1);
        weatherData.setGustPeak(12.0);
        weatherData.setLocation("test");
        weatherData.setPrecipitation(5.0);
        weatherData.setPressureSeaLevelQFF(1012.0);
        weatherData.setPressureSeaLevelQNH(1008.0);

        weatherDataSchema = new SMNWeatherDataJSONSchema();
    }

    @Test
    public void serialize() throws Exception {
        byte[] serializedData = weatherDataSchema.serialize(weatherData);
        String jsonData = new String(serializedData);
        assertEquals(weatherDataJson,jsonData);
    }

    @Test
    public void deserialize() throws Exception {
        SMNWeatherData deserializedData = weatherDataSchema.deserialize(weatherDataJson.getBytes());
        assertEquals(weatherData,deserializedData);
    }

}
