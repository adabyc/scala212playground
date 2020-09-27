package Collections

import org.scalatest.Matchers
import scalaz.Scalaz._

/**
  * Traversable -> Iterable -> Seq
  *
  *       IndexedSeq
  *
  *         Vector
  *         ResizableArray
  *         GenericArray
  *
  * Add seq :+ x
  *     x +: seq
  */
object Sequences extends App with Matchers {

  val s = Seq(1, 2, 3)

  """Add last (append)"""
  val s1 = s :+ 4
  s1 shouldBe Seq(1,2,3,4)

  """ Add head (prepend) """
  val s2 = 0 +: s1
  s2 shouldBe Seq(0,1,2,3,4)

  """ SORTING """
  """ sorting with implicit Ordered[T]"""
  val sInt = Seq(3,1,4,5,2)
  sInt.sorted shouldBe Seq(1,2,3,4,5)

  val sTup = Seq((1,9), (3,1), (2,5))
  """Sorting with implicit Ordered[T] where T is a returned value of seq elem"""
  sTup.sortBy(x => x._2) shouldBe Seq((3,1), (2,5),(1,9))
  """ sorting by comparison so subsequent elements"""
  sTup.sortWith((x1, x2) => x1._2 < x2._2) shouldBe Seq((3,1), (2,5),(1,9))

}

