#!/bin/bash

brokers=(0 1 2)

# dont need this, control-c will terminate all of the port forwards
# Check command line arguments
if [ "$1" == "stop" ]; then
  for broker in "${brokers[@]}"; do
    p=$(lsof -n -P -F -iTCP:919${broker} -sTCP:LISTEN | head -1 | cut -c2-)
    if [ ! -z $p ]; then
      echo $p
      kill $p
    fi
  done
  exit 0
fi

#
# broker discovery, keep that at 9092 -- this way locally same discovery can be used.
#
kubectl -n apps port-forward "broker-0" "9092:9190" &

#
# port forward to the stateful sets
#
for broker in "${brokers[@]}"; do
  echo "${broker}"
  echo kubectl -n apps port-forward "broker-${broker}" "919${broker}:919${broker}"
  kubectl -n apps port-forward "broker-${broker}" "919${broker}:919${broker}" &
done

#
# port forward the services
#
kubectl -n apps port-forward service/schema-registry 8081:8081 &
kubectl -n apps port-forward service/connect 8083:8083 &
kubectl -n apps port-forward service/postgres 5432:5432 &

wait
