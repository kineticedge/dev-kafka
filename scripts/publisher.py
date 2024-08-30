#!/usr/bin/python3

import time
from confluent_kafka import Producer

# Define the configuration for the Kafka producer.
conf = {'bootstrap.servers': 'localhost:9092'}

# Initialize the Kafka producer.
producer = Producer(conf)

while True:
    current_time = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime())
    print("publishing to kafka 2 messages.")
    producer.produce('foo', key="a", value=f'message-1 generated at {current_time}')
    producer.produce('foo', key="a", value=f'message-2 generated at {current_time}')
    producer.flush()
    time.sleep(0.001) # Sleep for 100 milliseconds.
