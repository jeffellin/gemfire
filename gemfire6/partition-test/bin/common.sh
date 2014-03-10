#!/bin/bash
###
# description: Settings that are common for all Gemfire services
###

source $BASE/conf/setenv.sh

GEMFIRE_WORK=$BASE/work



for jarfile in `ls -1 $BASE/app/lib/*.jar 2> /dev/null`
do
  APP_JARS=$APP_JARS:$jarfile
done

# App classes
APP_CLASSES=$BASE/app/classes:$APP_JARS


CLASSPATH=$APP_CLASSES:$CLASSPATH