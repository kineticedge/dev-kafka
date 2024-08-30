#!/bin/bash

cd "$(dirname -- "$0")"

../scripts/connect create ./connectors/postgres-users.json
../scripts/connect create ./connectors/datagen-users.json
