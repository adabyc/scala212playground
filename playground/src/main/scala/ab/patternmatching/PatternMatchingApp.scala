package ab.patternmatching

import org.scalatest.Matchers

object PatternMatchingApp extends App with Matchers {

  val seq = Seq(1,2,3)
  val tail = seq match {
    case Seq(h, t@_*) => t
  }

  tail shouldBe Seq(2,3)
}
