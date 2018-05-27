package ch.bfh.ti.nussa2.bti7311.source;

import ch.bfh.ti.nussa2.bti7311.model.SMNWeatherData;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.time.Instant;
import java.util.Date;
import java.util.Iterator;

public class SMNWeatherSource implements SourceFunction<SMNWeatherData> {

    private static final int SECS_TO_WAIT = 600;
    private volatile boolean isRunning = true;
    private Instant lastCall;

    @Override
    public void run(SourceContext<SMNWeatherData> ctx) throws Exception {

        Iterator<SMNWeatherData> dataIterator = fetchDataFromAPI();
        int i = 0;

        while (isRunning) {
            if (dataIterator.hasNext()) {
                i++;
                SMNWeatherData element = dataIterator.next();
                element.setFetchTime(Date.from(lastCall));
                ctx.collect(element);
            } else if (Instant.now().isAfter(lastCall.plusSeconds(SECS_TO_WAIT))) {
                System.out.println("fetched " + i + " elements from api at " + lastCall);
                i = 0;
                dataIterator = fetchDataFromAPI();
            }
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }

    private Iterator<SMNWeatherData> fetchDataFromAPI() {
        lastCall = Instant.now();
        return new SMNDataImporter().importData().iterator();
    }


}
