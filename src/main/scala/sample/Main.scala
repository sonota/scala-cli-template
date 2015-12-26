package sample

import scopt.OptionParser
import sample.model.Model

object Main {

  case class Config(
    h: Boolean = false,
    env: String = "devel"
  )

  def main(args: Array[String]):Unit = {
    println("Main")
    args.foreach { arg =>
      println("arg=" + arg)
    }

    var restArgs:List[String] = List()

    val parser = new OptionParser[Config]("scala-cli-template") {
      head("scala-cli-template", "0.0.1")
      help("help") text("prints this usage text")

      opt[String]("env") action { (x, c) =>
        c.copy(env = x)
      }

      arg[String]("...") unbounded() optional() action { (x, c) =>
        restArgs = restArgs :+ x
        c
      } text("optional unbounded args")
    }

    parser.parse(args, Config()) map { config =>
      println("env=" + config.env)
      println("restArgs=" + restArgs)
      Model.main(restArgs)
    } getOrElse {
      println("TODO")
    }
  }

}
