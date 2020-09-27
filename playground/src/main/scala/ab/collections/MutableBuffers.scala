package CollectionsMutable
import scala.collection.mutable._
import scalaz.Scalaz._
import org.scalatest._

/**
  * Add / remove to collection
  *   buff += x
  *   x +=: buff
  *   buff remove (x)
  *
  *   buff ++= bufX
  */
object MutableBuffers extends App with Matchers {

  """ Buffers are used to create sequences of elements incrementally by appending, pre-pending, or inserting new elements."""

  """ListBuffer, ArrayBuffer"""
  def bufferOp(buff: Buffer[Int])(buf2: Buffer[Int]): Buffer[Int] = {

    /** single element ops **/
    """ Add at begining += (append) """

    buff += 1
    buff+=(2)
    buff.+=(3)
    buff shouldBe Buffer(1,2,3)

    """ Add at the end +=: (prepend) """
    0 +=: buff
    buff shouldBe Buffer(0,1,2,3)

    """Remove @"""
    buff.remove(1)
    buff shouldBe Buffer(0,2,3)

    """Set element in place"""
    buff(1) = 22
    buff shouldBe Buffer(0,22,3)


    /** collections vs collections ops **/
    buff ++= buf2
    buff shouldBe Buffer(0, 22, 3, 7, 8, 9)

    buf2 ++=: buff
    buff shouldBe Buffer(7, 8, 9, 0, 22, 3, 7, 8, 9)

    buff
  }

  bufferOp(ArrayBuffer.empty[Int])(ArrayBuffer(7,8,9)) shouldBe a[ArrayBuffer[Int]]
  bufferOp(ListBuffer.empty[Int])(ListBuffer(7,8,9)) shouldBe a[ListBuffer[Int]]


}
