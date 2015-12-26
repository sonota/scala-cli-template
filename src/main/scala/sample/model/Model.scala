package sample.model

object Model {

  def main(args: Array[String]): Unit = {
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

}
