package ab.hkt

import ab.{Result, WrongWithEx, WrongWithMsg}
import org.scalatest.Matchers

import scala.util.{Failure, Success, Try}

object TypeClassWithHktApp extends App with Matchers {

  import ToResultInstances._
  import ToResultSyntax._

  val `try`: Try[Int] = Success(777)
  `try`.toResult shouldBe Right(777)

  val opt = Option.empty[String]
  opt.toResult shouldBe Left(WrongWithMsg("Cannot be empty"))

  println("DONE")
}

trait ToResult[F[_]] {
  def mkResult[T](ef: F[_]): Result[T]
}

object ToResultSyntax {

  implicit class ToResultOps[F[_]](ef: F[_]) {
    def toResult[T](implicit ev: ToResult[F]): Result[T] =
      ev.mkResult[T](ef)
  }
}

object ToResultInstances {

  implicit val tryToResult: ToResult[Try] = new ToResult[Try] {
    override def mkResult[T](ef: Try[_]): Result[T] = ef match {
      case Success(x: T) => Right(x)
      case Failure(ex) => Left(WrongWithEx("Not good", ex))
    }
  }

  implicit val optionToResult: ToResult[Option] = new ToResult[Option] {
    override def mkResult[T](ef: Option[_]): Result[T] = ef match {
      case Some(x: T) => Right(x)
      case None => Left(WrongWithMsg("Cannot be empty"))
    }
  }
}