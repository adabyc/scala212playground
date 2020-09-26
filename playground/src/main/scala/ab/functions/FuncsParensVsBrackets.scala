package ab.functions

object FuncsParensVsBrackets extends App {

  // ###########  Last argument parens can be replaced with curly brackets.

  // Curried function with two parameters: value and function
  def fun1(x: Int)(f: Int => String): String = f(x)

  val r1a = fun1(44)(s => s.toString + " - last parameter in ROUND BRACKETS")
  println(r1a)

  val r1b = fun1(44) { x => x.toString + " - last parameter in CURLY BRACKETS" }
  println(r1b)


  // Curried function with one parameter: function
  def fun2(f: Int => String): String = f(44)

  val r2a = fun2(x => x.toString + " - the only parameter in ROUND BRACES")
  println(r2a)

  val r2b = fun2 { x => x.toString + " - the only parameter in CURLY BRACKETS" }
  println(r2b)


  // Curried function with one parameter: parameterless function
  def fun3(f: () => String): String = f()

  val r3a = fun3(() => " - the only parameter in ROUND BRACES")
  println(r3a)
  val r3b = fun3 { () => " - the only parameter in CURLY BRACKETS" }
  println(r3b)


  // Curried function with one parameter: value
  def fun4(x: String): String = "44 - " + x

  val r4a = fun4(" - the only parameter value with ROUND BRACES")
  println(r4a)
  val r4b = fun4 {
    " - the only parameter value with CURLY BRACKETS"
  }
  println(r4b)

  // Curried non-trivial function with one parameter: value
  def makeListOfStrings(x: Int): List[String] = {
    List[String](x.toString, (x * x).toString, (x * x * x).toString)
  }

  val r5a = makeListOfStrings {
    val arg1 = 40;
    val arg2 = 4;
    val res = arg1 + arg2
    res
  }
  println(r5a)

}