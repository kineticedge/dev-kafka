package io.kineticedge.sample;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SampleTest extends BaseTestContainerTest {

  private static final Logger log = LoggerFactory.getLogger(SampleTest.class);

  @Test
  void sampleTest() {

    final String topic = createRandomTopic(2);

    try (KafkaProducer<String, String> producer =  new KafkaProducer<String, String>(
            Map.ofEntries(
                    Map.entry(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers()),
                    Map.entry(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class),
                    Map.entry(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
            )
    )) {
      Future<RecordMetadata> future = producer.send(new ProducerRecord<>(topic, null, "KEY", "VALUE"), (rm, e) -> {
       log.info(rm.topic());
      });
      try {
        Assertions.assertEquals(0, future.get().offset());
      } catch (ExecutionException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
