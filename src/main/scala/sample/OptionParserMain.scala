package sample

import scopt.OptionParser
import sample.model.Model

object OptionParserMain {

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
    val mainArgs = args.toList.drop(2)

    var restArgs:List[String] = List()

    val parser = new OptionParser[Config]("scala-cli-template") {
      head("scala-cli-template", "0.0.1")
      help("help") text("prints this usage text")

      opt[String]("profile") action { (x, c) =>
        c.copy(profile = x)
      }

      arg[String]("...") unbounded() optional() action { (x, c) =>
        restArgs = restArgs :+ x
        c
      } text("optional unbounded args")
    }

    parser.parse(mainArgs, Config()) map { config =>
      println("profile=" + config.profile)
      println("restArgs=" + restArgs)
      Model.main(restArgs)
    } getOrElse {
      println("TODO")
    }
  }

}
