package sample

object SubcmdMain {

  def main(args: Array[String]):Unit = {
    println("SubcmdMain")
    args.foreach { arg =>
      println("arg=" + arg)
    }
  }

}
