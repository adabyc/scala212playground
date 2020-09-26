package ab.functions

object FuncsAndCase extends App {

  val f: Int => Int = {
    case 1 => 0
    case x => x * x
  }

  println(f(1))
  println(f(3))

}
