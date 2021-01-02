package ab.skala

import ab.oop.AOPApp.convertToAnyShouldWrapper

import scala.util.Try

object ForComprehensionApp extends App {

  val resultWithSuccess = for {
    a <- getA()
    b = getB()
  } yield {
    sumAnB(a,b) // this has to be raw value and it is handled by for comprehension
  }
  println(resultWithSuccess)
  resultWithSuccess.isSuccess shouldBe true

  val errorInsideFor = for {
    a <- getA()
    b = getBEx()
  } yield {
    sumAnB(a,b)
  }
  println(errorInsideFor)
  errorInsideFor.isFailure shouldBe true

  val errorInsideYield = for {
    a <- getA()
    b = getB()
  } yield {
    sumAnBEx(a,b)
  }
  println(errorInsideYield)
  errorInsideYield.isFailure shouldBe true

  def getA(): Try[Int] = Try(1)

  def getB(): Int = 2
  def getBEx(): Int = {
    throw new NullPointerException("fail")
    2
  }

  def sumAnB(a: Int, b:Int): Int = a + b
  def sumAnBEx(a: Int, b:Int): Int = {
    throw new IllegalArgumentException("fail here")
    a + b
  }
}
