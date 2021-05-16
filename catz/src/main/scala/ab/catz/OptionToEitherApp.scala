package ab.catz

import org.scalatest.matchers.should.Matchers

object OptionToEitherApp extends App with Matchers {

  type Result[T] = Either[Throwable, T]

  val ex: Exception = new NullPointerException("some ex")
  val opt = Option[String] {
    null
  }

  val either: Either[Exception, String] = opt.toRight(ex)
  either shouldBe Left(ex)
}
