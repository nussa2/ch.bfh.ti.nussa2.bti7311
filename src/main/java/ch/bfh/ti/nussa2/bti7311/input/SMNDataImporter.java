package ch.bfh.ti.nussa2.bti7311.input;

import ch.bfh.ti.nussa2.bti7311.model.SMNWeatherData;
import ch.bfh.ti.nussa2.bti7311.model.SMNWeatherData;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class SMNDataImporter {

    public List<SMNWeatherData> importData (){


        URL swissMetNet = null;

        List<SMNWeatherData> meteoData = new ArrayList<>();

        URLConnection connection = null;

        try {
            swissMetNet = new URL("http://data.geo.admin.ch/ch.meteoschweiz.swissmetnet/VQHA69.csv");
            connection = swissMetNet.openConnection();

            String redirect = connection.getHeaderField("Location");

            if (redirect != null){
                connection = new URL(redirect).openConnection();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));){

            meteoData = new CsvToBeanBuilder(in).withType(SMNWeatherData.class).withEscapeChar("-".toCharArray()[0]).withSkipLines(2).withSeparator('|').build().parse();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return meteoData;
    }
}
