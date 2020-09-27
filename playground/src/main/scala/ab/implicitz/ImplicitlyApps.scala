package ab.implicitz

import org.scalatest.Matchers

/** Defines API (operations) for T */
trait MyOp[T] {
  def toStr(t: T): String
}

object MyOp {
  def apply[T : MyOp]: MyOp[T] = implicitly[MyOp[T]]
}

object ImplicitsImpl {
  implicit val strOps = new MyOp[String] {
    override def toStr(t: String): String = s"String.toStr $t"
  }
}

object ImplicitGetterApp extends App with Matchers {

  import ImplicitsImpl._

  /** gets implicit instance for given type */
  val inst = implicitly[MyOp[String]]

  inst.toStr("Text") shouldBe "String.toStr Text"
}

object ImplicitFuncParameterSyntacticSugarApp extends App with Matchers {

  import ImplicitsImpl._

  useImplicit("Text")

  /** This is syntactic sugar for implicit parameter MuOp[String] */
  def useImplicit[String: MyOp](str: String) = {
    val inst = implicitly[MyOp[String]]
    inst.toStr(str) shouldBe "String.toStr Text"
  }
}

object ImplicitFuncParameterSyntacticSugarAndApplyApp extends App with Matchers {

  import ImplicitsImpl._

  useImplicit("Text")

  /** This is syntactic sugar for implicit parameter MuOp[String]
    * We can more implicitly operator to apply, hence access instance by calling Type */
  def useImplicit[String: MyOp](str: String) = {
    MyOp[String].toStr(str) shouldBe "String.toStr Text"
  }
}

object ImplicitlyAndWrappersApp extends App with Matchers {

  /** Implicit val provides implementation */
  implicit val OpForIntImpl = new MyOp[Int] {
    override def toStr(d: Int): String = s"Integer=$d"
  }

  /** Usage of MyOp api with generic type T
    * [T:W] is a syntactic sugar for implicit parameter: fun(implicit arg W[T])
    */
  def useMyOp[T: MyOp](i: T): Unit = {
    implicitly[MyOp[T]].toStr(i) shouldBe s"Integer=$i"
  }

  useMyOp(44)
}

//object
object ImplicitlyProg extends App {

  implicit val impl = Wrapper[Int](123)

  def customImplicitly[T](implicit e: T): T = e

  def explicitImplicit[T](x: T)(implicit w: Wrapper[T]): String = x.toString + w.value

  val y1 = explicitImplicit(44)
  println(y1)

  /** Implicit param syntatic sugar */


  def shortcut4Implicit[T: Wrapper](x: T): String = x.toString + customImplicitly[Wrapper[T]].value

  val y2 = shortcut4Implicit(77)
  println(y2)

  def shortcut4ImplicitWithApply[T: Wrapper](x: T): String = x.toString + Wrapper[T].value

  val y3 = shortcut4ImplicitWithApply(79)
  println(y3)
}

case class Wrapper[T](x: T) {
  def value: String = "w" + x.toString
}

object Wrapper {
  def apply[A: Wrapper]: Wrapper[A] = implicitly[Wrapper[A]]
}


