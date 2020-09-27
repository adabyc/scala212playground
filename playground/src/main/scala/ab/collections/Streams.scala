package Collections
import scalaz.Scalaz._

object Streams extends App {


  /** Create stream */
  val stream: Stream[Int] = 1 #:: 2 #:: Stream.empty[Int]
  stream.foreach(println(_))

  val primes = Stream.cons(2, Stream.from(3, 2))
  primes.takeWhile(_ < 40) |> println

}
