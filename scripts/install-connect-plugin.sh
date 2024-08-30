#!/bin/bash

#
# an alternate to confluent-hub
#

if ! [ -x "$(command -v jq)" ]; then
    echo ""
    echo "jq is not found, please install and make it available on your path, https://stedolan.github.io/jq"
    echo ""
    exit
fi

if [ $# -lt 2 ]; then
  echo "usage: $0 owner name"
  exit
fi

cd "$(dirname -- "$0" )/.."

AUTHOR=$1
shift

CONNECTOR=$1
shift

METADATA_URL="https://www.confluent.io/hub/page-data"

response="$(curl -H "accept: application/json" --silent "${METADATA_URL}/${AUTHOR}/${CONNECTOR}/page-data.json")"

if [[ "$response" =~ ^\<\!DOCTYPE[[:space:]]+html ]]; then
  echo "connector $AUTHOR/$CONNECTOR not found"
  exit 1
fi

version=$(jq -r '.result.data.hubPlugin.version' <<< "${response}")

echo "found version=${version}"

url=$(jq -r '.result.data.hubPlugin.archive.url' <<< "${response}")

file=$(mktemp)

curl -s -o ${file} ${url}
unzip -o -d ./clusters/connect/plugins ${file}
/bin/rm ${file}

#OWNER=debezium
#NAME=debezium-connector-mysql
#PAGE_DATA="$(curl --silent "https://www.confluent.io/hub/page-data/${OWNER}/${NAME}/page-data.json")"
#jq -r '.result.data.hubPlugin.version' <<< "${PAGE_DATA}"
#jq -r '.result.data.hubPlugin.archive.url' <<< "${PAGE_DATA}"
