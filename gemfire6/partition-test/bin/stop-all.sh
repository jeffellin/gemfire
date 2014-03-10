OLDPWD=$PWD
cd `dirname $0`
BIN=`pwd`;BASE=`dirname $BIN`
cd $OLDPWD

source $BASE/bin/gemfire-locator stop

source $BASE/bin/gemfire-server stop 1

source $BASE/bin/gemfire-server stop 2

source $BASE/bin/gemfire-agent stop