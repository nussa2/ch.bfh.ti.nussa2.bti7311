package ch.bfh.ti.nussa2.bti7311.source;

import ch.bfh.ti.nussa2.bti7311.input.SMNDataImporter;
import ch.bfh.ti.nussa2.bti7311.model.SMNWeatherData;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.time.Instant;
import java.util.Iterator;

public class SMNWeatherSource implements SourceFunction<SMNWeatherData> {

    private static final int SECS_TO_WAIT = 1;
    private volatile boolean isRunning = true;
    private Instant lastCall;

    @Override
    public void run(SourceContext<SMNWeatherData> ctx) throws Exception {

        Iterator<SMNWeatherData> dataIterator = fetchDataFromAPI();

        while (isRunning) {
            if (dataIterator.hasNext()) {
                ctx.collect(dataIterator.next());
            } else if (Instant.now().isAfter(lastCall.plusSeconds(SECS_TO_WAIT))) {
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
