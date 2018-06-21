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

_package(){
  (
    cd "$PROJECT_DIR"
    $SBT_CMD assembly
  )
}

# --------------------------------
# Main

CURRENT_DIR=$(pwd)
PROJECT_DIR=$(_get_project_dir)

source "${PROJECT_DIR}/common.sh"

case "$1" in
  package)
    _package
    ;;
  *)
    echo "invalid argument"
    exit 1
    ;;
esac
