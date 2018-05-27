/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.bfh.ti.nussa2.bti7311.jobs;

import ch.bfh.ti.nussa2.bti7311.model.SMNWeatherData;
import ch.bfh.ti.nussa2.bti7311.source.KafkaSourceConfigurator;
import ch.bfh.ti.nussa2.bti7311.timing.PeriodicMaxOutOfOrderGenerator;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import java.util.Date;

/**
 * Job sums up the sunshine duration for one day for every station.
 */
public class SumStationSunshineDurationJob {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.getConfig().setAutoWatermarkInterval(1000);

        DataStream<SMNWeatherData> stream = env
                .addSource(KafkaSourceConfigurator.createKafkaSource())
                .assignTimestampsAndWatermarks(new PeriodicMaxOutOfOrderGenerator());

        DataStream<SMNWeatherData> sunshineSumStream = stream
                .filter(smnWeatherData -> smnWeatherData.getSunshineDuration() != null)
                .keyBy(SMNWeatherData::getLocation)
                .timeWindow(Time.hours(1))
                .sum("sunshineDuration");

        DataStream<Tuple3<String, Date, Double>> flatResultStream = sunshineSumStream.flatMap(new FlatMapFunction<SMNWeatherData, Tuple3<String, Date, Double>>() {
            @Override
            public void flatMap(SMNWeatherData smnWeatherData, Collector<Tuple3<String, Date, Double>> collector) throws Exception {
                collector.collect(new Tuple3<>(smnWeatherData.getLocation(), smnWeatherData.getTime(), smnWeatherData.getSunshineDuration()));
            }
        });

        flatResultStream.print();

        env.execute("SumStationSunshineDurationJob");
    }
}
