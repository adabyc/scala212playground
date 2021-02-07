package Collections

import org.scalatest.matchers.should.Matchers
// TODO: ITERABLES INTRODUCES VERY LITTLE COMPARING TO TRAVERSABLE => MOVE MOST OF THE CODE FROM HERE TO TRAVERSABLE
/**
  * foreach(f: T=? U): Unit
  * iterator
  *
  * zip
  * zipAll
  * zipWithIndex
  *
  * grouped
  * sliding
  *
  * takeRight
  * dropRight
  *
  * sameElements
  *
  * collectFirst: Option[T]
  *
  */
object IterableOps extends App with Matchers {

  val numIterable: Iterable[Int] = getIterable(1, 2, 3)
  val numIter: Iterable[Int] = getIterable(5, 6)
  val strIter: Iterable[String] = getIterable("a", "b", "c", "d", "e", "f", "g", "h").toVector
  /**
    * Addition, concatenation
    */

  """ ++ concatenates two iterables into new one
    |If types are different then result type is same as left hand side
    |++: same as ++ but if operands have different types the result is the same as right hand side
  """.stripMargin
  numIterable ++ numIter shouldBe Iterable(1,2,3,5,6)
  numIterable.size shouldBe 3
  numIter.size shouldBe 2

  Vector(5) ++ List(5) shouldBe Vector(5,5)
  Vector(5) ++: List(5) shouldBe Vector(5,5)

  /**
    * basic ops
    */

  """ sameElements - test two iterables having the same elements"""
  numIterable sameElements List(1, 2, 3) shouldBe true

  """ zip - two iterables into iterable of tuples """
  numIterable zip numIter shouldBe Iterable((1, 5), (2, 6))

  """zipWithIndex"""
  numIter.zipWithIndex shouldBe Iterable((5, 0), (6, 1))

  """zipAll - zips two iterables and fills with default elements"""
  numIterable.zipAll(numIter, -1, -2) shouldBe Iterable((1, 5), (2, 6), (3, -2))


  /** Iterator of Iterables */

  """grouped - iterator of iterables with given size"""
  strIter.grouped(3) sameElements Iterable(
    Iterable("a", "b", "c"),
    Iterable("d", "e", "f"),
    Iterable("g", "h"))
    .iterator

  """ sliding - iterator of iterables which are shifted by x"""
  strIter.sliding(3, 2) sameElements Iterable(
    Iterable("a", "b", "c"),
    Iterable("c", "d", "e"),
    Iterable("e", "f", "g"),
    Iterable("g", "h"))
    .iterator

  /** collecting */
  """collect"""
  strIter.collect {
    case "b" => "B"
    case "d" => "D"
  } sameElements Iterable("B", "D")

  """collectFirst - maps 1st element defined for PF and returns Option"""
  strIter.collectFirst {
    case "b" => "B"
    case "d" => "D"
  } sameElements Option("B")

  // TODO - add remaining elements

  def getIterable[T](items: T*): Iterable[T] = {
    items.toList
  }
}
