package ab.functions

/**
  * Call by Name does not evaluate arguments first, but only when they are accessed.
  */
object CallByName extends App {

  def getStr() = {
    println("2. getting string - called second")
    "str_val"
  }

  def printByName(x: => String): Unit = {
    println("1. printing string - called first")
    println(x)
  }

  printByName(getStr())

}

object CallByNameAandCurlyBrackets extends App {

  def strArg(): String = {
    println("calling strArg()...");
    Thread.sleep(93);
    "Arg 1 " + System.currentTimeMillis()
  }

  def callByNameFunc(arg: => String) {
    println("callByNameFunc called: " + arg)
    "callByNameFunc ret val: " + arg
  }

  // call call by name twice => arg is executed twice
  println("call-by-name")
  callByNameFunc(strArg)
  callByNameFunc {
    strArg
  }
  println("done")

  // call by name with curly brackets
  println("\ncurly-brackets")
  val res = callByNameFunc {
    "that arg is being passed via curly brackets - block"
  }
  println(res)


}
