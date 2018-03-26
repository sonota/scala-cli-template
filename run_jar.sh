#!/bin/bash

_get_project_dir() {
  if [ -L $0 ] ; then
    # シンボリックリンク
    dirname $(readlink $0)
  else
    echo $(cd $(dirname $0); pwd)
  fi

  return 0
}

# --------------------------------
# Main

CURRENT_DIR=$(pwd)
PROJECT_DIR=$(_get_project_dir)

source "${PROJECT_DIR}/common.sh"

if [ "$1" = "build" ]; then
  $SBT_CMD assembly
else
  java -cp target/scala-2.11/scala-cli-template-assembly-0.0.1.jar \
    sample.Main \
    "${CURRENT_DIR}" "${PROJECT_DIR}" "$@"
fi
