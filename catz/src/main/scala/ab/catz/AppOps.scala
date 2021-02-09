package ab.catz

import java.time.LocalTime
import java.util.concurrent.atomic.AtomicInteger

trait AppOps {

  val counter = new AtomicInteger(0)

  def thName = Thread.currentThread().getName

  def logThTime = {
    val t = LocalTime.now()
    println(s"$thName $t")
  }

  def logThTime(m: String) = {
    val t = LocalTime.now()
    println(s"$thName $t - $m")
  }
}
