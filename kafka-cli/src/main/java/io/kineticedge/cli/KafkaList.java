package io.kineticedge.cli;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ConsumerGroupListing;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaList {

  public static void main(String[] args) {

    if (args.length == 0) {
      return;
    }

    Map<String, String> argMap = parseArgs(args);

    String bootstrapServers = argMap.get("bootstrap-server");
    String commandConfigFile = argMap.get("command-config");

    final Map<String, Object> config = new HashMap<>();
    config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    config.putAll(toMap(load(commandConfigFile)));

    String command = args[0];

    switch (command) {
      case "topics":
        topics(config).forEach(System.out::println);
        break;
      case "groups":
        groups(config).forEach(System.out::println);
        break;
      default:
    }

  }

  private static List<String> topics(final Map<String, Object> config) {
    try (AdminClient adminClient = AdminClient.create(config)) {
      return adminClient.listTopics().names().get().stream().sorted().toList();
    } catch (final ExecutionException | InterruptedException e) {
      Thread.currentThread().interrupt();
      return List.of();
    }
  }

  private static List<String> groups(final Map<String, Object> config) {
    try (AdminClient adminClient = AdminClient.create(config)) {
      return adminClient.listConsumerGroups().all().get().stream().map(ConsumerGroupListing::groupId).sorted().toList();
    } catch (final ExecutionException | InterruptedException e) {
      Thread.currentThread().interrupt();
      return List.of();
    }
  }

  private static Properties load(final String file) {
    Properties properties = new Properties();

    if (file == null) {
      return properties;
    }

    try (FileInputStream configStream = new FileInputStream(file)) {
      properties.load(configStream);
    } catch (Exception e) {
      // ignore
    }
    return properties;
  }

  private static Map<String, Object> toMap(final Properties properties) {
    Map<String, Object> map = new HashMap<>();
    for (String name : properties.stringPropertyNames()) {
      map.put(name, properties.getProperty(name));
    }
    return map;
  }

  private static Map<String, String> parseArgs(String[] args) {
    Map<String, String> argMap = new HashMap<>();
    for (int i = 1; i < args.length; i++) {
      if (args[i].startsWith("--")) {
        String key = args[i].substring(2);
        if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
          argMap.put(key, args[++i]);
        }
      }
    }
    return argMap;
  }
}
