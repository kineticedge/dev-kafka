#!/bin/bash

cd "$(dirname -- "$0")"

(cd ../clusters/main; docker compose down -v)