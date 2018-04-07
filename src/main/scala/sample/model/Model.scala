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

  def cat(isInspect: Boolean): Unit = {
    // println(isInspect)

    val r:java.io.Reader = new java.io.InputStreamReader(System.in, "UTF-8")
    val w:java.io.Writer = new java.io.OutputStreamWriter(System.out, "UTF-8")

    var done = false
    var c:Int = -1
    while (! done) {
      c = r.read()
      if (c < 0) {
        done = true
      } else {
        if (isInspect) {
          if (c == '\t') {
              w.write("^I")
          } else if (c == '\r') {
              w.write("^M")
          } else if (c == '\n') {
              w.write("$\n")
          } else {
              w.write(c)
          }
        } else {
          w.write(c)
        }
      }
    }

    w.flush()
    w.close()

    r.close()
  }

}
