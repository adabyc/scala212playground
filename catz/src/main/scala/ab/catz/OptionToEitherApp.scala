package ab.catz

import org.scalatest.matchers.should.Matchers

//import scala.util.Try
//import cats.implicits._

object OptionToEitherApp extends App with Matchers {

  type Result[T] = Either[Throwable, T]

  val ex = new NullPointerException("some ex")
  val opt = Option[String] {
    null
  }

  val either = opt.toRight(ex)
  either shouldBe Left(ex)
}
