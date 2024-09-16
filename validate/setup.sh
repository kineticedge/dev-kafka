#!/bin/bash

cd "$(dirname -- "$0")"

../scripts/install.sh confluentinc kafka-connect-datagen
../scripts/install.sh confluentinc kafka-connect-jdbc

