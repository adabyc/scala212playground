package ab.catz

import scala.util.Try
import cats.implicits._
import org.scalatest.Matchers

object ExceptionToEitherApp extends App with Matchers {

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

object TryToEitherApp extends App with Matchers {

  type Result[T] = Either[Throwable, T]

  val ex = new NullPointerException("some ex")
  val t = Try[Int] {
    throw ex
  }
  val newEx = new Exception("Wrapper Exception")
  val either = t.toEither.left.map(e => newEx)
  either shouldBe Left(newEx)
}
