package ab.misc

/**
  * Some basics for console line app
  */
object ConsoleApp extends App {

  println("Start console app")

  println("Type something in")

  val stdInVal = scala.io.StdIn.readLine()
  println(stdInVal)

  sys.addShutdownHook{
    println("SHUTTING DOWN")
  }
}
