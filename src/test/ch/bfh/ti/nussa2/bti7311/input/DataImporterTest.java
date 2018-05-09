package ch.bfh.ti.nussa2.bti7311.input;

import ch.bfh.ti.nussa2.bti7311.model.MeteoData;

import org.junit.BeforeClass;

import java.util.List;

import static org.junit.Assert.*;

public class DataImporterTest {

    static DataImporter dataImporter;

    @BeforeClass
    public static void setup(){
        dataImporter = new DataImporter();
    }

    @org.junit.Test
    public void importData() throws Exception {

        List<MeteoData> data = dataImporter.importData();
        System.out.println(data);

        assertTrue(data.size() > 0);

    }

}
