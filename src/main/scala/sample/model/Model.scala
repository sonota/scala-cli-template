package sample.model

import scala.collection.mutable.ListBuffer

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
    val lines: ListBuffer[String] = ListBuffer()

    val r: java.io.Reader = new java.io.InputStreamReader(System.in, "UTF-8")
    var n: Int = 0
    val buf: ListBuffer[Integer] = ListBuffer()

    while (n >= 0) {
      n = r.read()
      if (n < 0) {
        // break
      } else {
        buf += n
        if (n == '\n') {
          lines += intListToString(buf.toList)
          buf.clear
        }
      }
    }
    lines += intListToString(buf.toList)
    buf.clear

    lines.toList
  }

  def intListToString(ns: List[Integer]): String = {
    String.valueOf(ns.map{ _.toChar }.toArray)
  }

  def sortLines(lines: List[String]): List[String] = {
    if (lines.size <= 1) {
      return lines
    }

    val pivot = lines(0)
    val left: ListBuffer[String] = ListBuffer()
    val center: ListBuffer[String] = ListBuffer()
    val right: ListBuffer[String] = ListBuffer()

    lines.foreach{ line => 
      val cmp = line.compareTo(pivot)
      if (cmp < 0) {
        left += line
      } else if (cmp > 0) {
        right += line
      } else {
        center += line
      }
    }

    val leftSorted: List[String] = sortLines(left.toList)
    val rightSorted: List[String] = sortLines(right.toList)

    List.concat(leftSorted, center.toList, rightSorted)
  }

  def hexdump(): Unit = {
    val is = System.in
    var ri = -1
    val buf: Array[Byte] = Array.fill[Byte](16)(0)
    var lastPos = 0
    var done = false
    while (!done) {
      ri += 1
      val len = is.read(buf)
      if (len == -1) {
        print("%08x\n".format(lastPos))
        done = true
      }
      if (!done) {
        print(hexdump_formatLine(ri, buf, len) + "\n")
        lastPos = ri * 16 + len
      }
    }
  }

  def hexdump_formatLine(ri: Int, buf: Array[Byte], len: Int): String = {
    var line = ""
    line += "%08x ".format(ri * 16)

    var ci = 0
    for (ci <- 0 until len) {
      if ((ci + 8) % 16 == 0) {
        line += " "
      }
      line += " %02x".format(buf(ci))
    }

    line
  }

}
