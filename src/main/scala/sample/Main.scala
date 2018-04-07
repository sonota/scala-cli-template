package sample

import sample.model.Model

object Main {

  case class Config(
    h: Boolean = false,
    profile: String = "profile"
  )

  def main(args: Array[String]):Unit = {
    println("Main")
    args.foreach { arg =>
      println("arg=" + arg)
    }

    val currentDir = args(0)
    val projectDir = args(1)
    val mainArgs = args.toList.drop(2).toArray

    val opts = Maparse.parseArgs(mainArgs, "arg1", "arg2")

    Model.main(mainArgs.toList)
  }

}
