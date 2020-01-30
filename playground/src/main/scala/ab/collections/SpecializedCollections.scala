package ab.collections

import org.scalatest.Matchers

object SpecializedCollections extends App with Matchers {

  /**
    * ListSet - Set preserving order of the items.
    */

  import scala.collection.immutable.ListSet

  val listSet = ListSet(1, 2, 3)
  listSet + 4 shouldBe ListSet(1, 2, 3, 4)


  /**
    * SortedSet (immutable & mutable) - Set with elements sorting
    */

  import scala.collection.SortedSet
  import scala.collection.immutable.{TreeSet}

  val sortedSet = SortedSet(3, 9, 6, 3) // trait
  sortedSet shouldBe TreeSet(3, 6, 9) // class


  /**
    * ListMap (immutable & mutable) - map preserving order of the items.
    * Suitable for small collection due to expensive insert operation
    */

  import scala.collection.immutable.ListMap

  val listMap = ListMap(3 -> "three", 1 -> "one", 7 -> "seven")
  listMap + (4 -> "four") shouldBe Map(3 -> "three", 1 -> "one", 7 -> "seven", 4 -> "four")

  /**
    * SortedMap (immutable & mutable) - map with sorted elements by key
    */
  import scala.collection.SortedMap

  val sortedMap = SortedMap(3 -> "three", 1 -> "one", 7 -> "seven")
  sortedMap + (4 -> "four") shouldBe Map(1 -> "one", 3 -> "three", 4 -> "four", 7 -> "seven")

  // with custom key

  case class CustomKey(key: Int)

  implicit val customKeyOrdered = new Ordering[CustomKey]{
    override def compare(x: CustomKey, y: CustomKey): Int = x.key.compareTo(y.key)
  }
  val sortedMapWithKey = SortedMap(CustomKey(3) -> "three", CustomKey(1) -> "one", CustomKey(5) -> "five")
  sortedMapWithKey shouldBe Map(CustomKey(1) -> "one", CustomKey(3) -> "three", CustomKey(5) -> "five")


}
