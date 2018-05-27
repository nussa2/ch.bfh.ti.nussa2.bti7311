package ch.bfh.ti.nussa2.bti7311.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.io.Serializable;
import java.util.Date;

public class SMNWeatherData implements Serializable {

    /**
     * The location-id where the event origins.
     */
    @CsvBindByName(column = "stn")
    private String location;

    /**
     * The event time when the event was captured.
     */
    @CsvBindByName(column = "time")
    @CsvDate("yyyyMMddhhmm")
    private Date time;

    /**
     * The event time when the event was fetched from the api.
     */
    private Date fetchTime;

    /**
     * The air temperature in °C measured 2 m above ground. Current value measured at event time.
     */
    @CsvBindByName(column = "tre200s0")
    private Double airTemperature;

    /**
     * The sunshine duration in min. Ten minutes total.
     */
    @CsvBindByName(column = "sre000z0")
    private Double sunshineDuration;

    /**
     * The precipitation in mm. Ten minutes total.
     */
    @CsvBindByName(column = "rre150z0")
    private Double precipitation;

    /**
     * The wind direction in °. 0° = North. Ten minutes mean.
     */
    @CsvBindByName(column = "dkl010z0")
    private Double windDirection;

    /**
     * The wind speed in km/h. Ten minutes mean.
     */
    @CsvBindByName(column = "fu3010z0")
    private Double windSpeed;

    /**
     * The preassure in hPa reduced to sea level according to standard atmosphere (QNH); Current value measured at event time.
     */
    @CsvBindByName(column = "pp0qnhs0")
    private Double pressureSeaLevelQNH;

    /**
     * The gust peak in km/h (one second). Maximum value.
     */
    @CsvBindByName(column = "fu3010z1")
    private Double gustPeak;

    /**
     * The relative air humidity in % 2 m above ground. Current value measured at event time.
     */
    @CsvBindByName(column = "ure200s0")
    private Double relAirHumidity;

    /**
     * The pressure at station level (QFE) in hPa. Current value measured at event time.
     */
    @CsvBindByName(column = "prestas0")
    private Double pressureStationLevelQFE;

    /**
     * The pressure in hPa reduced to sea level (QFF). Current value measured at event time.
     */
    @CsvBindByName(column = "pp0qffs0")
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

    public Date getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(Date fetchTime) {
        this.fetchTime = fetchTime;
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
                ", fetchTime=" + fetchTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SMNWeatherData that = (SMNWeatherData) o;

        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (airTemperature != null ? !airTemperature.equals(that.airTemperature) : that.airTemperature != null)
            return false;
        if (sunshineDuration != null ? !sunshineDuration.equals(that.sunshineDuration) : that.sunshineDuration != null)
            return false;
        if (precipitation != null ? !precipitation.equals(that.precipitation) : that.precipitation != null)
            return false;
        if (windDirection != null ? !windDirection.equals(that.windDirection) : that.windDirection != null)
            return false;
        if (windSpeed != null ? !windSpeed.equals(that.windSpeed) : that.windSpeed != null) return false;
        if (pressureSeaLevelQNH != null ? !pressureSeaLevelQNH.equals(that.pressureSeaLevelQNH) : that.pressureSeaLevelQNH != null)
            return false;
        if (gustPeak != null ? !gustPeak.equals(that.gustPeak) : that.gustPeak != null) return false;
        if (relAirHumidity != null ? !relAirHumidity.equals(that.relAirHumidity) : that.relAirHumidity != null)
            return false;
        if (pressureStationLevelQFE != null ? !pressureStationLevelQFE.equals(that.pressureStationLevelQFE) : that.pressureStationLevelQFE != null)
            return false;
        return pressureSeaLevelQFF != null ? pressureSeaLevelQFF.equals(that.pressureSeaLevelQFF) : that.pressureSeaLevelQFF == null;
    }

    @Override
    public int hashCode() {
        int result = location != null ? location.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (airTemperature != null ? airTemperature.hashCode() : 0);
        result = 31 * result + (sunshineDuration != null ? sunshineDuration.hashCode() : 0);
        result = 31 * result + (precipitation != null ? precipitation.hashCode() : 0);
        result = 31 * result + (windDirection != null ? windDirection.hashCode() : 0);
        result = 31 * result + (windSpeed != null ? windSpeed.hashCode() : 0);
        result = 31 * result + (pressureSeaLevelQNH != null ? pressureSeaLevelQNH.hashCode() : 0);
        result = 31 * result + (gustPeak != null ? gustPeak.hashCode() : 0);
        result = 31 * result + (relAirHumidity != null ? relAirHumidity.hashCode() : 0);
        result = 31 * result + (pressureStationLevelQFE != null ? pressureStationLevelQFE.hashCode() : 0);
        result = 31 * result + (pressureSeaLevelQFF != null ? pressureSeaLevelQFF.hashCode() : 0);
        return result;
    }
}
