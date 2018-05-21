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

package ch.bfh.ti.nussa2.bti7311;

import ch.bfh.ti.nussa2.bti7311.model.SMNWeatherData;
import ch.bfh.ti.nussa2.bti7311.source.SMNWeatherSource;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * Skeleton for a Flink Streaming Job.
 * <p>
 * <p>For a tutorial how to write a Flink streaming application, check the
 * tutorials and examples on the <a href="http://flink.apache.org/docs/stable/">Flink Website</a>.
 * <p>
 * <p>To package your appliation into a JAR file for execution, run
 * 'mvn clean package' on the command line.
 * <p>
 * <p>If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */
public class StreamingJob {

    public static void main(String[] args) throws Exception {
        // set up the streaming execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<SMNWeatherData> stream = env
                .addSource(new SMNWeatherSource());
                stream.filter(smnWeatherData -> smnWeatherData.getAirTemperature() != null);
                stream.map(new MapFunction<SMNWeatherData, SMNWeatherData>() {
                    @Override
                    public SMNWeatherData map(SMNWeatherData smnWeatherData) throws Exception {
                        double celcius = smnWeatherData.getAirTemperature();
                        double fahrenheit = celcius * 1.8 + 32;
                        smnWeatherData.setAirTemperature(fahrenheit);
                        return smnWeatherData;
                    }
                });
                /*.assignTimestampsAndWatermarks(new AscendingTimestampExtractor<SMNWeatherData>() {
                    @Override
                    public long extractAscendingTimestamp(SMNWeatherData element) {
                        return element.getTime().getTime();
                    }
                })

                //.filter(smnWeatherData -> smnWeatherData.getSunshineDuration() != null && smnWeatherData.getSunshineDuration() > 0.0)
                //.filter(smnWeatherData -> smnWeatherData.getLocation().equals("PAY"));

                //.keyBy(SMNWeatherData::getLocation)
                //.timeWindow(Time.seconds(4))
                //.window(TumblingProcessingTimeWindows.of(Time.seconds(10)))
                //.window(TumblingEventTimeWindows.of(Time.seconds(10)))
                //.sum("sunshineDuration");

        DataStream<SMNWeatherData> filteredStream = stream
                .filter(smnWeatherData -> smnWeatherData.getSunshineDuration() != null && smnWeatherData.getSunshineDuration() > 0.0);

        KeyedStream<SMNWeatherData, String> stationKeyedStream = filteredStream
                .keyBy(SMNWeatherData::getLocation);*/

        DataStream<Tuple2<String, Double>> sunshineSumStream = stream.flatMap(new FlatMapFunction<SMNWeatherData, Tuple2<String, Double>>() {
            @Override
            public void flatMap(SMNWeatherData smnWeatherData, Collector<Tuple2<String, Double>> collector) throws Exception {
                collector.collect(new Tuple2<>(smnWeatherData.getLocation(),smnWeatherData.getSunshineDuration()));
            }
        });

        //sunshineSumStream.print();
        stream.print();

		/*
		 * Here, you can start creating your execution plan for Flink.
		 *
		 * Start with getting some data from the environment, like
		 * 	env.readTextFile(textPath);
		 *
		 * then, transform the resulting DataStream<String> using operations
		 * like
		 * 	.filter()
		 * 	.flatMap()
		 * 	.join()
		 * 	.coGroup()
		 *
		 * and many more.
		 * Have a look at the programming guide for the Java API:
		 *
		 * http://flink.apache.org/docs/latest/apis/streaming/index.html
		 *
		 */

        // execute program
        env.execute("Flink Streaming Java API Skeleton");
    }
}
