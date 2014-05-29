# Get script directory
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
export JAVA_HOME=`/usr/libexec/java_home -v 1.7`

export GEMFIRE_VERSION=7

export GEMFIRE=/Users/jellin/Downloads/Pivotal_GemFire_702_b45797
export GFE_CONFIG_DIR=$DIR/../conf
export GFE_LOG_DIR=$DIR/../log
export DEFAULT_LOCATOR_HOST=localhost
export DEFAULT_LOCATOR_PORT=41111
export DEFAULT_CLUSTER=NearRealTimeRisk
export DEFAULT_NODES=2
export GFE_APP_JAR_DIR=$DIR/../lib

export PATH=$PATH:$GEMFIRE/bin