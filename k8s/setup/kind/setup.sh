#!/bin/bash

set -e

cd "$(dirname -- "$0")"

DIR=$(cd ../../..; pwd)

#
# Need to use absolute path in configuration, so update template
#
sed "s|{{DIR}}|${DIR}|g" ./kind-config-template.yaml > kind-config.yaml

kind create cluster --config=./kind-config.yaml

kubectl create namespace apps

kubectl config set-context --current --namespace=apps

