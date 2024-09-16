package io.kineticedge.sample;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public abstract class BaseTestContainerTest {

  private static final Logger log = LoggerFactory.getLogger(BaseTestContainerTest.class);

  private static final String IMAGE = "apache/kafka-native:latest";
  //private static final String IMAGE = "apache/kafka:latest";
  //private static final String IMAGE = "confluentinc/cp-kafka:7.7.0";

  protected static final KafkaContainer kafka = new KafkaContainer(
          DockerImageName.parse(IMAGE).asCompatibleSubstituteFor("apache/kafka"))
          .withStartupTimeout(Duration.ofSeconds(15))
          .withLogConsumer(outputFrame -> log.info(outputFrame.getUtf8String()));

  @BeforeAll
  public static void baseBeforeClass() {
    kafka.start();
  }

  @AfterAll
  public static void baseAfterClass() {
    kafka.stop();
  }

  protected static String createRandomTopic(final int partitions) {
    return createRandomTopics(1, partitions).getFirst();
  }

  protected static List<String> createRandomTopics(final int count, final int partitions) {

    final List<String> topics = IntStream.range(0, count)
            .mapToObj(i -> RandomStringUtils.randomAlphabetic(20))
            .toList();

    try (AdminClient client = KafkaAdminClient.create(
            Map.of(
                    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers()
            ))
    ) {
      final List<NewTopic> newTopics = topics.stream().map(t -> new NewTopic(t, partitions, (short) -1)).toList();
      client.createTopics(newTopics);
    }

    log.info("created topics {}.", topics);

    return topics;
  }

}
