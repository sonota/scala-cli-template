package sample

import sample.model.Model

object SubcmdMain {

  case class ConfigA(
    h: Boolean = false,
    profile: String = "devel"
  )

  case class ConfigB(
    h: Boolean = false,
    profile: String = "devel"
  )

  def main(args: Array[String]):Unit = {
    println("SubcmdMain")
    args.foreach { arg =>
      println("arg=" + arg)
    }

    val currentDir = args(0)
    val projectDir = args(1)
    val cmd = args(2)
    val mainArgs:List[String] = args.toList.drop(3)

    var restArgs:List[String] = List()

    if( cmd == "cmd_a" ) {
      val opts = Maparse.parseArgs(mainArgs.toArray, "arg1", "arg2")

      Model.cmdA(mainArgs)

    }else if( cmd == "cmd_b" ) {
      val opts = Maparse.parseArgs(mainArgs.toArray, "arg1", "arg2")

      Model.cmdB(mainArgs)

    }else if( cmd == "cat" ) {
      Model.cat()

    }else{
      println(s"unsupported sub command (${mainArgs(0)})")
    }
  }

}
