package ab.jvmthreading

case class Tazk(var id: Int) extends Runnable with Loger {
  override def run(): Unit = {
    try {
      showT(s"Running 1000 ms task $id...")
      Thread.sleep(1000)
    } catch {
      case e: InterruptedException =>
        errorT(s"Task interrupted $e")
    }
  }
}
