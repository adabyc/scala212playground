package ab.jvmthreading

/**
  * Trait has been name with spelling error intentionally to not confuse with common logging frameworks.
  */
trait Loger {

  def show(msg: String) = println(s"${Thread.currentThread().getName} (${Thread.currentThread().getId}) $msg")
  def showT(msg: String) = println(s"${java.time.LocalTime.now} ${Thread.currentThread().getName} (${Thread.currentThread().getId}) $msg")
  def errorT(msg: String) = System.err.println(s"${java.time.LocalTime.now} ${Thread.currentThread().getName} (${Thread.currentThread().getId}) $msg")
}
