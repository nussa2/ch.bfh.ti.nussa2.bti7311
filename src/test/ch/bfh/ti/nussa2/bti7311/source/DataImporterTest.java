package ch.bfh.ti.nussa2.bti7311.source;

import ch.bfh.ti.nussa2.bti7311.model.SMNWeatherData;

import ch.bfh.ti.nussa2.bti7311.source.SMNDataImporter;
import org.junit.BeforeClass;

import java.util.List;

import static org.junit.Assert.*;

public class DataImporterTest {

    static SMNDataImporter dataImporter;

    @BeforeClass
    public static void setup(){
        dataImporter = new SMNDataImporter();
    }

    @org.junit.Test
    public void importData() throws Exception {

        List<SMNWeatherData> data = dataImporter.importData();
        System.out.println(data);

        assertTrue(data.size() > 0);

    }

}
