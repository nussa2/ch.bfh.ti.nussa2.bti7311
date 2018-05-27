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
import ch.bfh.ti.nussa2.bti7311.model.SMNWeatherDataJSONSchema;
import ch.bfh.ti.nussa2.bti7311.source.SMNWeatherSource;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;

/**
 * Job that queries SMN Weather Data every ten minutes and saves it to Kafka.
 */
public class DataPreparationJob {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<SMNWeatherData> stream = env
                .addSource(new SMNWeatherSource());

        FlinkKafkaProducer011<SMNWeatherData> myProducer = new FlinkKafkaProducer011<SMNWeatherData>(
                "localhost:9092",            // broker list
                "weather",                  // target topic
                new SMNWeatherDataJSONSchema());   // serialization schema

        // versions 0.10+ allow attaching the records' event timestamp when writing them to Kafka;
        // this method is not available for earlier Kafka versions
        myProducer.setWriteTimestampToKafka(true);

        stream.addSink(myProducer);

        env.execute("Prepare Weather Data for Kafka");
    }
}
