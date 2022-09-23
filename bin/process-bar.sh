#!/bin/bash

CLASS="com.icafe.command.ProcessBarDriver"

SELF_DIR="$(cd "$(dirname "$0")"; pwd)"

RUNNER="${JAVA_HOME}/bin/java"

$RUNNER -cp "${SELF_DIR}/*" $CLASS $@
