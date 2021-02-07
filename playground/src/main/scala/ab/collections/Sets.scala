package Collections

import org.scalatest.matchers.should.Matchers

/**
  * Sets basics
  *
  * Addition:
  *   set + x
  *   set + (x,y)
  *   set ++ set2
  *
  *   set - x
 */
object Sets extends App with Matchers {

  val set = Set(1,2,3,3)
  val set1 = Set(8,9)

  """Addition"""
  set + 4 shouldBe Set(1,2,3,4)
  set + (4,5,6) shouldBe Set(1,2,3,4,5,6)
  set ++ set1 shouldBe Set(1,2,3,8,9)

  """ Removal"""
  set - 2 shouldBe Set(1,3)



}
