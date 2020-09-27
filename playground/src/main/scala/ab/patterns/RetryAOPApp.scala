package ab.patterns

import org.scalatest.Matchers

import scala.util.{Success, Try}

/**
  * Pattern components:
  * 1. trait with operation - defines abstract "interface"
  * 2. implementation of the basic operation which extends trait
  * 3. trait extending abstract "interface" with abstract override operation,
  * which executes cross cutting concern before and/or after basic operation
  *
  * ref: Scala Design Patterns: Timing our application with AOP; p98
  */
object RetryAOPApp extends App with Matchers {

  val retry4Times = new ThirdTimeFailsImpl with RetryClient { max = 4}
  retry4Times.get("goodUrl") shouldBe Success("retry left: 0")

  //  val retry2Times = new ThirdTimeFailsImpl with RetryClient {max = 2}
  //  retry2Times.get("badUrl").isFailure shouldBe true

}

trait UnreliableClient {
  def get(url: String): Try[String]
}

trait RetryClient extends UnreliableClient {
  var max: Int = _
  abstract override def get(url: String): Try[String] = {
    def retry(i: Int): Try[String] = i match {
      case 0 => super.get(url)
      case _ => super.get(url) match {
        case s@Success(_) => s
        case _ => retry(i - 1)
      }
    }
    retry(max)
  }
}

class ThirdTimeFailsImpl extends UnreliableClient {
  var counter = 4

  override def get(url: String) = {
    counter = counter - 1
    if (counter > 0) Try(throw new UnsupportedOperationException("bad url"))
    else Try("retry left: " + counter)
  }
}

