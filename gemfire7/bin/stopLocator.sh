#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Source environment variables
source $DIR/env.sh

PORT=$1
DSI=$2


LOCATOR_LOG_DIR=$DIR/../log/site$PORT/locator

if [ $GEMFIRE_VERSION -eq 6 ]; then
	gemfire stop-locator -dir=$LOCATOR_LOG_DIR -port=$PORT
else
	gfsh stop locator --dir=$LOCATOR_LOG_DIR
fi