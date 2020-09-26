package ab.functions

/**
  * REF http://www.vasinov.com/blog/on-currying-and-partial-function-application/
  * Currying and partial function application allow to create specialized functions based on general functions.
  * In general use partially applied functions for all of your specialized functions.
  * Currying is better when dealing with blocks, dynamic default parameter values, smart type inference, or multiple varargs.
  */

object CurryVsPartial extends App {

  partiallyAppliedFunction()

  currying()

  def funcSumOf3(a: Int, b: Int, c: Int): Int = a + b + c

  def multiplyAndPlus(a: Int, b: Int, x: Int): Int = a * x + b

  def partiallyAppliedFunction(): Unit = {

    /**
      * In functional programming languages, calling a function with parameters is described as applying the function to the parameters.
      * When all the parameters are passed to the function — something you always do in Java — you have fully applied the function to all of the parameters.
      * But when you give only a subset of the parameters to the function, the result of the expression is a partially applied function.
      */

    println("\n                                              PARTIALLY APPLIED FUNCTION")

    // _ vs partially applied function
    val f = funcSumOf3 _ // here _ means that none of the 3 required parameters have been applied
    println(s"fun _ is ${f}")

    def temp(op: (Int, Int, Int) => Int) = op(1, 2, 3)

    temp(funcSumOf3) // omitting _ is only allowed where function is expected, then compiler can infer what is required

    // apply 2 out of 3 params
    val fun1 = funcSumOf3(1, 10, _: Int)
    val r1 = fun1(5)
    println(s"Applied 2 params: ${fun1} result: ${r1}")

    val fun2 = funcSumOf3(1, _: Int, _: Int)
    val r2 = fun2(10, 5)
    println(s"Applied 1 params: ${fun2} result: ${r2}")

    val fun2to1 = fun2(10, _: Int)
    val r2to1 = fun2to1(5)
    println(s"Applied 2nd time 1 param: ${fun2to1} result: ${r2to1}")

    // shortcut for partial function
    def partialFun = funcSumOf3(1, _: Int, 5)

    println(s"Partially Applied function(1, _:Int, 5) result: ${partialFun(10)}")
  }

  def currying(): Unit = {
    /**
      * Currying is the process of decomposing a function of multiple arguments into a chained sequence of functions of one argument.
      * f(a,b,x)=a∗x+b   --->   g(x)=x↦(b↦(a↦f(a,b,x)))
      * formally:
      * f:(X×Y×Z)→N
      * curry(f):X→(Y→(Z→N))
      *
      */
    println("\n                                              CURRYING")


    def curriedLine(a: Int)(b: Int)(x: Int): Int = multiplyAndPlus(a, b, x)

    val r0 = curriedLine(2)(3)(4)
    val curriedFuntionValue = curriedLine(_)
    val r1 = curriedFuntionValue(2)(3)(4)
    println(s"Curried curriedFuntionValue ${curriedFuntionValue} result: ${r1}")

    def defaultLine(x: Int) = curriedLine(2)(3)(x)

    val defaultLineFv = defaultLine(_)
    val r2 = defaultLineFv(4)
    println(s"Curried defaultLineFv ${defaultLineFv} result: ${r2}")

    // NOTE: we can supply any parameter
    def funBy_b(b: Int) = curriedLine(2)(b)(4)

    val funBy_b_Fv = funBy_b(_)
    val r3 = funBy_b_Fv(3)
    println(s"Curried funBy_b_Fv ${funBy_b_Fv} result: ${r3}")


    // SCALA language currying
    def curriedByScala = (multiplyAndPlus _).curried

    def funBy_a(a: Int) = curriedByScala(a)(3)(4)

    val funBy_a_Fv = funBy_a(_)
    val r4 = funBy_a_Fv(2)
    println(s"Curried funBy_a_Fv ${funBy_a_Fv} result: ${r4}")


    def funWithFixed_b(a: Int, x: Int) = curriedByScala(a)(3)(x)

    println(s"Curried funWithFixed_b result: ${funWithFixed_b(2, 4)}")

  }

  def functionBlocks(): Unit = {

    def functionTemplate_Block(op: () => Unit): Unit = {
      print("Open Transaction -> ")
      op()
      println(" -> Close Transaction")
    }

    functionTemplate_Block { () =>
      print("Save do database")
    }


    def curriedFunctionBlock(s: String)(op: (String) => Int): Unit = {
      println(s"Length of string ${s} is ${op(s)} ")
    }

    curriedFunctionBlock("Adam") { x => x.length }
  }

  def closures(): Unit = {

    /**
      * Scala closures capture variables not values !!!
      */

    var freeVar = 10

    val fun = (x: Int) => x + freeVar
    println(s"call closure ${fun(4)}")
    freeVar = 20
    println(s"call closure after free var being modified ${fun(4)}") // 24 !!!
  }
}
