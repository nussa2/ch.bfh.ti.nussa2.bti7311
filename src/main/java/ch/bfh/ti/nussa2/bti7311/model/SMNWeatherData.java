package ch.bfh.ti.nussa2.bti7311.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.util.Date;

public class SMNWeatherData {

    @CsvBindByName(column = "stn")
    private String location;

    @CsvBindByName(column = "time")
    @CsvDate("yyyyMMddhhmm")
    private Date time;

    @CsvBindByName(column = "tre200s0")
    //�C 2 m above ground; current value
    private Double airTemperature;

    @CsvBindByName(column = "sre000z0")
    //min ten minutes total
    private Double sunshineDuration;

    @CsvBindByName(column = "rre150z0")
    //mm ten minutes total
    private Double precipitation;

    @CsvBindByName(column = "dkl010z0")
    //� ten minutes mean
    private Double windDirection;

    @CsvBindByName(column = "fu3010z0")
    //km/h ten minutes mean

    private Double windSpeed;

    @CsvBindByName(column = "pp0qnhs0")
    //hPa reduced to sea level according to standard atmosphere (QNH); current value
    private Double pressureSeaLevelQNH;

    @CsvBindByName(column = "fu3010z1")
    //km/h (one second); maximum
    private Double gustPeak;

    @CsvBindByName(column = "ure200s0")
    //% 2 m above ground; current value
    private Double relAirHumidity;

    @CsvBindByName(column = "prestas0")
    //hPa current value
    private Double pressureStationLevelQFE;

    @CsvBindByName(column = "pp0qffs0")
    //hPa reduced to sea level (QFF); current value
    private Double pressureSeaLevelQFF;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(Double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public Double getSunshineDuration() {
        return sunshineDuration;
    }

    public void setSunshineDuration(Double sunshineDuration) {
        this.sunshineDuration = sunshineDuration;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }

    public Double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Double windDirection) {
        this.windDirection = windDirection;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getPressureSeaLevelQNH() {
        return pressureSeaLevelQNH;
    }

    public void setPressureSeaLevelQNH(Double pressureSeaLevelQNH) {
        this.pressureSeaLevelQNH = pressureSeaLevelQNH;
    }

    public Double getGustPeak() {
        return gustPeak;
    }

    public void setGustPeak(Double gustPeak) {
        this.gustPeak = gustPeak;
    }

    public Double getRelAirHumidity() {
        return relAirHumidity;
    }

    public void setRelAirHumidity(Double relAirHumidity) {
        this.relAirHumidity = relAirHumidity;
    }

    public Double getPressureStationLevelQFE() {
        return pressureStationLevelQFE;
    }

    public void setPressureStationLevelQFE(Double pressureStationLevelQFE) {
        this.pressureStationLevelQFE = pressureStationLevelQFE;
    }

    public Double getPressureSeaLevelQFF() {
        return pressureSeaLevelQFF;
    }

    public void setPressureSeaLevelQFF(Double pressureSeaLevelQFF) {
        this.pressureSeaLevelQFF = pressureSeaLevelQFF;
    }

    @Override
    public String toString() {
        return "SMNWeatherData{" +
                "location='" + location + '\'' +
                ", time=" + time +
                ", airTemperature=" + airTemperature +
                ", sunshineDuration=" + sunshineDuration +
                ", precipitation=" + precipitation +
                ", windDirection=" + windDirection +
                ", windSpeed=" + windSpeed +
                ", pressureSeaLevelQNH=" + pressureSeaLevelQNH +
                ", gustPeak=" + gustPeak +
                ", relAirHumidity=" + relAirHumidity +
                ", pressureStationLevelQFE=" + pressureStationLevelQFE +
                ", pressureSeaLevelQFF=" + pressureSeaLevelQFF +
                '}';
    }
}
