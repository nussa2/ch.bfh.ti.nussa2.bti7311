package ch.bfh.ti.nussa2.bti7311.operators;

import ch.bfh.ti.nussa2.bti7311.model.SMNWeatherData;

public class AvgTemperatureState {
    public String key;
    public long count;
    public double sum;
    public SMNWeatherData lastElement;
}
