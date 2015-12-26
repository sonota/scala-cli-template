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

    val mainArgs:List[String] = args.toList.tail
    var restArgs:List[String] = List()

    if( args(0) == "cmd_a" ) {
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

      parser.parse(args, ConfigA()) map { config =>
        println("env=" + config.env)
        println("restArgs=" + restArgs)
        Model.cmdA(restArgs)
      } getOrElse {
        println("TODO")
      }

    }else if( args(0) == "cmd_b" ) {
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

      parser.parse(args, ConfigB()) map { config =>
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
