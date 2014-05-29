#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Source environment variables
source $DIR/env.sh

PORT=$1
DSI=$2

if [ -z $DSI ]
then
	echo "Setting default distributed-system-id to -1."
    DSI=-1
fi

HEAP="--J=-Xms64m --J=-Xmx64m"

LOCATOR_LOG_DIR=$GFE_LOG_DIR/site$PORT/locator

DIRECTORY=$GFE_LOG_DIR/site$PORT
if [ ! -d "$DIRECTORY" ]; then
	echo "Creating site directory for port $DIRECTORY"
        mkdir $DIRECTORY
fi

if [ ! -d "$LOCATOR_LOG_DIR" ]; then
	echo "Creating $LOCATOR_LOG_DIR"
        mkdir $LOCATOR_LOG_DIR
fi

if [ -f "$LOCATOR_LOG_DIR/.locator" ]; then
        echo "Removing $LOCATOR_LOG_DIR/.locator lock file."
        rm $LOCATOR_LOG_DIR/.locator
fi

# Startup locator
if [ $GEMFIRE_VERSION -eq 6 ]; then
	gemfire start-locator -peer=true -server=true -dir=$LOCATOR_LOG_DIR -properties=$GFE_CONFIG_DIR/locator/gemfire.properties -Dgemfire.log-file=$LOCATOR_LOG_DIR/locator.log -Dgemfire.name=gemfire.LOCATOR -port=$PORT
else 
	gfsh start locator --name=locator-$DSI $HEAP --dir=$LOCATOR_LOG_DIR --properties-file=$GFE_CONFIG_DIR/locator/site$PORT/gemfire.properties --port=$PORT 
fi