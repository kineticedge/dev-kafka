#!/bin/bash

set -e
cd "$(dirname -- "$0")"

KACC='kafka-avro-console-consumer \
	--bootstrap-server localhost:9092 \
	--property schema.registry.url=http://localhost:8081 \
	--property print.key=true \
	--property key.separator=| \
	--from-beginning \
	--skip-message-on-error \
	--key-deserializer=org.apache.kafka.common.serialization.BytesDeserializer \
	--max-messages 2 \
	--topic'

KACC_string='kafka-avro-console-consumer \
	--bootstrap-server localhost:9092 \
	--property schema.registry.url=http://localhost:8081 \
	--property print.key=true \
	--property key.separator=| \
	--from-beginning \
	--skip-message-on-error \
	--key-deserializer=org.apache.kafka.common.serialization.StringDeserializer \
	--max-messages 2 \
	--topic'

echo "string deserializer"

$KACC_string users_avro

echo "byte deserializer"

$KACC users_avro
