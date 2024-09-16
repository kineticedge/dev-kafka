#!/bin/bash

# Get rid of the annoying message on exit:
#   What's next?
#     Try Docker Debug for seamless, persistent debugging tools in any container or image → docker debug dk-minimal-broker-1
#     Learn more at https://docs.docker.com/go/debug-cli/
export DOCKER_CLI_HINTS=false

if [ $# -eq 0 ]; then
  echo "usage: the first argument must be an actual kafka command as defined on the broker."
  exit
fi

BROKER=dk-minimal-broker-1

#if a symbolic link was used for each command, you could pull command from name of symbolic link
#COMMAND="$(basename "$0")"
COMMAND=$1
shift

CMD="$COMMAND --bootstrap-server localhost:9092 $*"

#
# add the interactive and terminal settings "-it" if this is a console command.
#
if [[ $COMMAND == *"-console-"* ]]; then
 IT="-it"
else
 IT=""
fi

docker exec $IT $BROKER sh -c "$CMD"

