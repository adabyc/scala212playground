package ab.functions

/**
  * function literal - a function with no name in Scala source code, specified with function literal syntax.
  * eg: (x:Int, y:Int) => x + y
  * function literal is compiled into class that when instantiated at runtime is a function value.
  * *
  * function value - function object that can be invoked just like any other function. A function value's class extends one of traits FunctionN.
  * Function value is "invoked" when its apply method is called.
  * Function value that captures free variables is a closure.
  * *
  * Note: function literals exist in a source code whereas function values exist as objects at runtime (similar to classes - source code and objects)
  * *
  * Free variable - a variable that is used inside the expression but not defined inside the expression.
  * eg: (x:Int) => x + y // y is a free variable
  */
object FunctionsBasics extends App {

  def workWithFun(op: (String) => Int): Unit = println(s"Result of op is " + op("test arg"))

  def myOperation(x: String) = x.length

  //                                        FUNCTION LITERALS

  // argument is function literal
  // 1.
  workWithFun((x: String) => myOperation(x))
  workWithFun((x) => myOperation(x))
  workWithFun(x => myOperation(x))
  // If function literal consist of one statement and takes one arg then, there is no need to explicitly name argument.
  // this shorthand is partially applied function
  workWithFun(myOperation)
  workWithFun(_.length) // !!!
  workWithFun(myOperation(_))
  workWithFun(myOperation _) // chapter 8.5


  //                                      FUNCTION VALUE
  println()
  val fun1 = (x: Int) => x * x
  println(s"Classic call as fun(4) ${fun1(4)}")
  println(s"Real Scala function value invocation as fun.apply(4) ${fun1.apply(4)}")

  val fun2 = (_: Int) + (_: Int) // shorthand
  println(s"Shorthanded function value (_:Int) + (_:Int) ${fun2(4, 4)}")

}
