package sample

object Main {

  def main(args: Array[String]):Unit = {
    println("Main")
    args.foreach { arg =>
      println("arg=" + arg)
    }
  }

}
