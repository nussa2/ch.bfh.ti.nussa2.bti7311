package ch.bfh.ti.nussa2.bti7311.source;

import ch.bfh.ti.nussa2.bti7311.model.SMNWeatherData;
import ch.bfh.ti.nussa2.bti7311.model.SMNWeatherDataJSONSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

import java.util.Properties;

public class KafkaSourceConfigurator {

    public static FlinkKafkaConsumer011<SMNWeatherData> createKafkaSource() {
        Properties kafkaProperties = new Properties();
        kafkaProperties.setProperty("bootstrap.servers", "localhost:9092");
        kafkaProperties.setProperty("group.id", "weather-group");

        FlinkKafkaConsumer011<SMNWeatherData> flinkWeatherDataConsumer = new FlinkKafkaConsumer011<SMNWeatherData>("weather", new SMNWeatherDataJSONSchema(), kafkaProperties);
        flinkWeatherDataConsumer.setStartFromEarliest();

        return flinkWeatherDataConsumer;
    }


}
