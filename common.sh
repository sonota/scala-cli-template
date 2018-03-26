# SBT_CMD="/path/to/sbt-x.x.x/bin/mvn"
SBT_CMD="sbt"

_exec_sbt(){
  local main_class="$1"; shift

  $SBT_CMD "runMain ${main_class} $*"
}
