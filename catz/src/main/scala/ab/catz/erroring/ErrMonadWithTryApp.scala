package ab.catz.erroring

import cats.MonadError
import cats.effect.IO

import scala.util.Try // for MonadError

/**
  * Try with MonadError
  */
object ErrMonadWithTryApp extends App {

  val tryMonadErr: MonadError[Try, Throwable] = MonadError[Try, Throwable]
  val underlyingMonadTry: Try[Int] = tryMonadErr.pure(777)
  assert(underlyingMonadTry == Try(777))


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
