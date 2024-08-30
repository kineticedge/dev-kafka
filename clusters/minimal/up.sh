#!/bin/bash

set -e

cd "$(dirname -- "$0")"

docker compose up -d $(docker compose config --services | egrep -v -e "(schema-registry|connect)")

