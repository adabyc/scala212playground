package ab.catz

import java.util.concurrent.Executors
import scala.concurrent.duration.DurationInt
import java.util.concurrent.ScheduledExecutorService

import cats.effect.IO

import scala.concurrent.duration.FiniteDuration
object IoApp extends App {


  def sleep(d: FiniteDuration)(implicit ec: ScheduledExecutorService): IO[Int] =
    IO.cancelable { cb =>
      // Schedules task to run after delay
      val run = new Runnable {
        def run() = {
          println("executing...")
          cb(Right(44))
        }
      }
      val future = ec.schedule(run, d.length, d.unit)

      // Cancellation logic, suspended in IO
      IO({
        println("cancelling")
        future.cancel(true)
      }).void
    }

  implicit val es = Executors.newScheduledThreadPool(1)
  val y = sleep(500 millis)

  val r = y.unsafeRunCancelable { e => {
    println(s"cancelled $e")
    ()
  }
  }

  println(r)

  Thread.sleep(3000)
  println("done")

}
