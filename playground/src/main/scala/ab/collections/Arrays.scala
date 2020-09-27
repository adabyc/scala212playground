package Collections

/**
  * Arrays basics
  */
object Arrays extends App {

  val a1 = Array(1,2,3)
  val a2 = Array(1,2,3)
  val a2b = a2

  /** Arrays equality */
  assert(a1 != a2)
  assert(a2 == a2b)
}
