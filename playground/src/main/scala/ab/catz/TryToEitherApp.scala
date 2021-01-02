package ab.catz

import scala.util.Try
import cats.implicits._
import org.scalatest.Matchers

object TryToEitherApp extends App with Matchers {

  type Result[T] = Either[Throwable, T]
  type ResultEx[T] = Either[Exception, T]

  val ex = new NullPointerException("some ex")
//  val either: ResultEx[Int] = Either.catchNonFatal { // wont compile
  val either: Result[Int] = Either.catchNonFatal {
    throw ex
    777
  }
  either shouldBe Left(ex)
}
