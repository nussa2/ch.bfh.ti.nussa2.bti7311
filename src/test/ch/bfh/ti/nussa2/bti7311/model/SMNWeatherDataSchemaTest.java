package ch.bfh.ti.nussa2.bti7311.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SMNWeatherDataSchemaTest {

    SMNWeatherData weatherData;

    SMNWeatherDataSchema weatherDataSchema;

    @Before
    public void setUp() throws Exception {
        weatherData = new SMNWeatherData();
        weatherData.setAirTemperature(25.1);
        weatherData.setGustPeak(12.0);
        weatherData.setLocation("test");
        weatherData.setPrecipitation(5.0);
        weatherData.setPressureSeaLevelQFF(1012.0);
        weatherData.setPressureSeaLevelQNH(1008.0);

        weatherDataSchema = new SMNWeatherDataSchema();
    }

    @Test
    public void serialize() throws Exception {
        byte[] serializedData = weatherDataSchema.serialize(weatherData);
        assertTrue(serializedData != null && serializedData.length >0);
    }

    @Test
    public void deserialize() throws Exception {
        byte[] serializedData = weatherDataSchema.serialize(weatherData);
        SMNWeatherData deserializedData = weatherDataSchema.deserialize(serializedData);
        assertEquals(weatherData,deserializedData);
    }

}
