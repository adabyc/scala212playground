package ab.jvmthreading

class Tazk(var id: Int) extends Runnable with Loger {
  override def run(): Unit = {
    try {
      showT(s"Running task $id...")
      Thread.sleep(1000)
      //      println(" Completed task {}", index)
    } catch {
      case e: InterruptedException => println(s"Task interrupted $e")
    }
  }
}
