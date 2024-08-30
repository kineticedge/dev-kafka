#!/bin/bash

cd "$(dirname -- "$0")"

TYPE=USER

if [ $# -lt 1 ]; then
 COUNT=1
else
 COUNT=$1
 shift
fi

function number() {
  echo $(($RANDOM % $1))
}

function random() {
  echo $(openssl rand -hex 5)
}

for i in $(seq $COUNT); do
  order_id=$(random)
  store_id=$(number 100)
  user_id=$(number 1000)
  first_name="FIRST_NAME_$user_id"
  last_name="LAST_NAME_$(random)"

  echo "creating order user_id=${user_id}, first_name=${first_name}, last_name=${last_name}"

  KEY="${user_id}"
  VALUE="{ \"user_id\": \"${user_id}\", \"first_name\": \"${first_name}\", \"last_name\": \"${last_name}\" }"

  echo "${KEY}|${VALUE}" | kafka-console-producer --bootstrap-server localhost:19092 --topic USERS --property parse.key=true --property key.separator=\| --producer-property acks=all
  #sleep 0.1

done

