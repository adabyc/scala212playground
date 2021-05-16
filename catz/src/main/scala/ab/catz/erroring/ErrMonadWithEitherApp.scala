package ab.catz.erroring

import cats.MonadError
import cats.effect.IO

/**
  * Either with MonadError
  */
object ErrMonadWithEitherApp extends App {

  type ErrorOr[A] = Either[Throwable, A]

  val eitherMonadErr: MonadError[ErrorOr, Throwable] = MonadError[ErrorOr, Throwable]
  val underlyingMonadEither = eitherMonadErr.pure(777)
  assert(underlyingMonadEither == Right(777))

  println(underlyingMonadEither)


  //  monadError.


  //  val io: IO[String] = getGoodIo()
  //
  //  val yio = io.flatMap{ s =>
  //    IO.raiseError(new IllegalArgumentException("not-good"))
  //  }
  //
  //
  //  val r = yio.unsafeRunSync()
  //  println(r)

  //    val result = for {
  //      y <- getSomeIo()
  //    } yield {
  //      ExitCode.Success
  //    }


  def getBadIo(): IO[String] = IO.raiseError(new IllegalArgumentException("bad"))

  def getGoodIo(): IO[String] = IO.pure("good-io")
}
