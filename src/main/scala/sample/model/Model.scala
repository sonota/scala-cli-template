package sample.model

object Model {

  def main(args: List[String]): Unit = {
    println("-->> Model.main")
    println(args)
    println(add(
      args(0).toInt,
      args(1).toInt
    ))
  }

  def add(a: Int, b: Int): Int = {
    a + b
  }

  def cmdA(args: List[String]): Unit = {
    println("-->> Model.cmdA")
    println(args)
  }

  def cmdB(args: List[String]): Unit = {
    println("-->> Model.cmdB")
    println(args)
  }

}
