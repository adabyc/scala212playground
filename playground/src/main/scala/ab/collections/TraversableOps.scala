package Collections

import org.scalatest.Matchers

/**
  * Operations
  *
  * ++
  * ++:
  *
  * slice (from, until)
  * filter (p)
  *
  * split = take n, drop n
  * span  = takeWhile p , dropWhile p
  * partition = filter p , filterNot p
  *
  * fold
  * scan
  *
  * groupBy
  *
  * maxBy
  *
  * mkString
  * addString ( sb, pre, sep, post)
  */
object TraversableOps extends App with Matchers {


  val trInts = Traversable(1,2,3,4,5,6,7,8)
  val trList: Traversable[Int] = List(1)
  val trVect: Traversable[Int] = Vector(3)
  val trStr: Traversable[String] = Traversable("a", "b")
  val trShort = Traversable(1,2,3,4)


  """ ++ concatenate and create new traversable """
  trList ++ trVect shouldBe List(1,3)
  trList.size shouldBe 1
  trVect.size shouldBe 1

  // TODO : what is the difference
  trList ++: trVect shouldBe Vector(1,3)

  """ SUB COLLECTIONS """

  """ slice ( from, until)"""
  trInts.slice(1,3) shouldBe Traversable(2,3)

  """ filter (p) / filterNot """
  trInts.filter(x => x > 2 && x < 6) shouldBe Traversable(3,4,5)


  """ SUBDIVISION"""

  """ splitAt ( n ) == take n & drop n"""
  trInts.splitAt(2) shouldBe (List(1,2), List(3,4,5,6,7,8))

  """ span == takeWhile p & dropWhile p"""
  trInts.span(x => x < 3) shouldBe (List(1,2), List(3,4,5,6,7,8))

  """ partition ( p ) == filter p & filterNot p"""
  trInts.partition(x => x > 2 && x < 5) shouldBe (List(3,4), List(1,2,5,6,7,8))

  """ GROUPING """

  """ group by """
  val groupedBy = trInts.groupBy(x => x % 3)
  groupedBy shouldBe Map(2 -> List(2, 5, 8), 1 -> List(1, 4, 7), 0 -> List(3, 6))

  val toGroup = Traversable(("a", 1), ("b", 2), ("a", 3))
  toGroup.groupBy(_._1) shouldBe Map("b" -> List(("b",2)), "a" -> List(("a",1), ("a",3)))


  """ MAX BY vs MAX"""
  val trIntsAsStrings = Traversable("9", "100")
  trIntsAsStrings.max shouldBe "9"

  trIntsAsStrings.maxBy(Integer.parseInt) shouldBe "100"

  """ FOLD """

  """ scan - like fold but returns each element per operation as traversable"""
  // 10 , 10+1, 11+2, 13+3, 16+4
  trShort.scan(10)((a,b) => a + b) shouldBe  Traversable(10, 11, 13, 16, 20)

  """ STRING """

  """ mkString """
  trStr.mkString("-") shouldBe "a-b"

  """ addString """
  val sb = new StringBuilder()
  trStr.addString(sb, "[", ",", "]")
  sb.mkString shouldBe "[a,b]"

  // TODO
  // VIEWS


}
