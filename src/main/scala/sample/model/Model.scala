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

  def sort(): Unit = {
    val lines: List[String] = readAllLines()
    val sorted: List[String] = sortLines(lines)

    for (line: String <- sorted) {
      print(line)
    }
  }

  def readAllLines(): List[String] = {
    var lines: List[String] = Nil

    val r: java.io.Reader = new java.io.InputStreamReader(System.in, "UTF-8")
    var n: Int = 0
    var buf: List[Integer] = Nil

    while (n >= 0) {
      n = r.read()
      if (n < 0) {
        // break
      } else {
        buf = List.concat(buf, List(n))
        if (n == '\n') {
          lines = List.concat(lines, List(intListToString(buf)))
          buf = Nil
        }
      }
    }
    lines = List.concat(lines, List(intListToString(buf)))
    buf = Nil

    lines
  }

  def intListToString(ns: List[Integer]): String = {
    var cs = new Array[Char](ns.size)

    ns.zipWithIndex.foreach{ case(n, i) =>
      cs(i) = n.toChar
    }

    String.valueOf(cs)
  }

  def sortLines(lines: List[String]): List[String] = {
    val pivot = lines(0)
    var left: List[String] = Nil
    var center: List[String] = Nil
    var right: List[String] = Nil

    lines.foreach{ line => 
      val cmp = line.compareTo(pivot)
      if (cmp < 0) {
        left = List.concat(left, List(line))
      } else if (cmp > 0) {
        right = List.concat(right, List(line))
      } else {
        center = List.concat(center, List(line))
      }
    }

    var leftSorted: List[String] = Nil
    if (left.size >= 2) {
      leftSorted = sortLines(left)
    } else {
      leftSorted = left
    }

    var rightSorted: List[String] = Nil
    if (right.size >= 2) {
      rightSorted = sortLines(right)
    } else {
      rightSorted = right
    }

    var result: List[String] = Nil
    result = List.concat(result, leftSorted)
    result = List.concat(result, center)
    result = List.concat(result, rightSorted)

    result
  }

}
