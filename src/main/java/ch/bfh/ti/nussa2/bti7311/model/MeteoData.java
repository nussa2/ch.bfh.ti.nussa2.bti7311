package ch.bfh.ti.nussa2.bti7311.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.util.Date;

public class MeteoData {

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

    @Override
    public String toString() {
        return "MeteoData{" +
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
