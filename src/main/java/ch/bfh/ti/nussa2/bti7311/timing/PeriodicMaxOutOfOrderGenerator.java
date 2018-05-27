package ch.bfh.ti.nussa2.bti7311.timing;

import ch.bfh.ti.nussa2.bti7311.model.SMNWeatherData;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import javax.annotation.Nullable;

public class PeriodicMaxOutOfOrderGenerator implements AssignerWithPeriodicWatermarks<SMNWeatherData> {

    private final long maxOutOfOrderness = 10000; // 10 sec

    private long currentMaxTimestamp;

    @Nullable
    @Override
    public Watermark getCurrentWatermark() {
        return new Watermark(currentMaxTimestamp - maxOutOfOrderness);
    }

    @Override
    public long extractTimestamp(SMNWeatherData element, long previousElementTimestamp) {
        long timestamp = element.getTime().getTime();
        currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp);
        return timestamp;
    }
}
