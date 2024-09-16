# Minimal Cluster

## Usage

* Use this cluster on machine with limited hardware.
* Make sure the `dev-kafka` network is created first, `../network.sh`.
  * the external network makes it easier to spin up other components in separate docker compose files, and access the connect cluster.
* See `up.sh` for starting cluster, including starting it w/out _schema registry_ or _connect cluster_.

## Summary

* A single node acting as a `broker` and `controller`.
  * KRaft (no zookeeper)
  * no metric reporting

* a single schema-registry

* a single kafka connect
  * use `./connect/plugins` to install connectors
  * use `./connect/data` if the plugin needs files from local file system (e.g. datagen).


