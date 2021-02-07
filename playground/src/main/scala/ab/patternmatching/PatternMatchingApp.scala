package ab.patternmatching

import org.scalatest.matchers.should.Matchers

object PatternMatchingApp extends App with Matchers {

  /** Seq */
  val seq = Seq(1,2,3)
  val tail = seq match {
    case Seq(h, t@_*) => t
  }

  tail shouldBe Seq(2,3)


  /** List */
  val l = List(1,2,3,4,5)
  val matchHeads = l match {
    case 1::2::2::t => t
  }
  matchHeads shouldBe List(4,5)
}
