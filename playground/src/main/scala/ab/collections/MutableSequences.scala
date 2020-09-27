package CollectionsMutable

import scalaz.Scalaz._

import scala.collection.mutable
import scala.collection.mutable.{ListBuffer, _}

object MutableSequences extends App {

  /**
    * Buffer
    */
  def bufferAdditions(buf: Buffer[Int], before: Buffer[Int], after: Buffer[Int]): Unit = {

    /** add head */
    4 +=: buf
    buf |> println // ListBuffer(4, 5)

    before ++=: buf
    buf |> println // ListBuffer(1, 2, 4, 5)

    /** add tail */
    buf += 6
    buf |> println // ListBuffer(1, 2, 4, 5, 6)

    buf ++= after
    buf |> println // ListBuffer(1, 2, 4, 5, 6, 8, 9)
  }

  def bufferRemoval(buf: Buffer[Int], before: Buffer[Int], after: Buffer[Int]): Unit = { // buf = ListBuffer(1, 2, 4, 5, 6, 8, 9)
    buf -= 5
    buf |> println // ListBuffer(1, 2, 4, 6, 8, 9)

    buf remove 1
    buf |> println // ListBuffer(1, 4, 6, 8, 9)

    buf remove(1, 2)
    buf |> println // ListBuffer(1, 8, 9)

    buf trimStart 1
    buf |> println // ListBuffer(8, 9)

    buf trimEnd 1
    buf |> println // ListBuffer(8)

    buf clear()
    buf |> println // ListBuffer()

  }

  val buf: mutable.Buffer[Int] = ListBuffer[Int](5)
  val before = ListBuffer[Int](1, 2)
  val after = ListBuffer[Int](8, 9)

  println("additions")
  bufferAdditions(buf, before, after)
  println("removals")
  bufferRemoval(buf, before, after)


}
