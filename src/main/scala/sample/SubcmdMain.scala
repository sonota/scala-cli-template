package sample

import scopt.OptionParser
import sample.model.Model

object SubcmdMain {

  case class ConfigA(
    h: Boolean = false,
    env: String = "devel"
  )

  case class ConfigB(
    h: Boolean = false,
    env: String = "devel"
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
      val parser = new OptionParser[ConfigA]("scala-cli-template") {
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

      parser.parse(mainArgs, ConfigA()) map { config =>
        println("env=" + config.env)
        println("restArgs=" + restArgs)
        Model.cmdA(restArgs)
      } getOrElse {
        println("TODO")
      }

    }else if( cmd == "cmd_b" ) {
      val parser = new OptionParser[ConfigB]("scala-cli-template") {
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

      parser.parse(mainArgs, ConfigB()) map { config =>
        println("env=" + config.env)
        println("restArgs=" + restArgs)
        Model.cmdB(restArgs)
      } getOrElse {
        println("TODO")
      }

    }else{
      println(s"unsupported sub command (${mainArgs(0)})")
    }
  }

}
