package ch.bfh.ti.nussa2.bti7311.operators;

import ch.bfh.ti.nussa2.bti7311.model.SMNWeatherData;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class AvgTemperature extends ProcessWindowFunction<SMNWeatherData, SMNWeatherData, Tuple, TimeWindow> {

    private ValueState<AvgTemperatureState> state;

    @Override
    public void process(Tuple key, Context context, Iterable<SMNWeatherData> elements, Collector<SMNWeatherData> out) throws Exception {
        state = context.windowState().getState(new ValueStateDescriptor<>("avgTemperatureState", AvgTemperatureState.class));
        AvgTemperatureState currentState = state.value();

        if (currentState == null) {
            currentState = new AvgTemperatureState();
            currentState.key = key.getField(0);
            currentState.sum = 0.0;
            currentState.count = 0;
        }

        for (SMNWeatherData element : elements) {
            currentState.sum += element.getAirTemperature();
            currentState.count++;
            currentState.lastElement = element;
            state.update(currentState);
        }

        double average = currentState.sum / currentState.count;
        currentState.lastElement.setAirTemperature(average);
        out.collect(currentState.lastElement);
        state.clear();
    }
}

