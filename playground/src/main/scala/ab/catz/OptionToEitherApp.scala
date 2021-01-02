package ab.catz

//import scala.util.Try
//import cats.implicits._
import org.scalatest.Matchers

object OptionToEitherApp extends App with Matchers {

  type Result[T] = Either[Throwable, T]

  val ex = new NullPointerException("some ex")
  val opt = Option[String] {
    null
  }

  val either = opt.toRight(ex)
  either shouldBe Left(ex)
}
