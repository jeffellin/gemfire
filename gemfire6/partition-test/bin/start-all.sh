#!/bin/bash
###
# description: Settings that are common for all Gemfire services
###

OLDPWD=$PWD
cd `dirname $0`
BIN=`pwd`;BASE=`dirname $BIN`
cd $OLDPWD

source $BASE/bin/gemfire-locator start

 $BASE/bin/gemfire-server start 1

 $BASE/bin/gemfire-server start 2

source $BASE/bin/gemfire-agent start