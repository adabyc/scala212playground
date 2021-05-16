package ab.catz

import cats.data._
import cats.implicits._
import org.scalatest.matchers.should.Matchers

import scala.concurrent.{Await, Future}
import scala.util.{Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

/**
  * For-comprehension example
  */
object OptionTInForApp extends App with Matchers {

  val goodAsyncResp1 = OptionT(Future.successful(Option("201")))
  val goodResp2 = Option("202")
  val goodAsyncResp3 = OptionT(Future.successful(Option("203")))

  /** all good */
  val aggregatedResp: OptionT[Future, String] = for {
    resp1 <- goodAsyncResp1
    resp2 = goodResp2
    resp3 <- goodAsyncResp3
  } yield {
    s"$resp1-$resp2-$resp3"
  }

  Await.result(aggregatedResp.value, 0 nanos) shouldBe Option("201-Some(202)-203")


  /** inner is bad (map)*/
  val badResp2 = Option.empty[String]

  val aggregatedInnerBadResp = for {
    resp1 <- goodAsyncResp1
    resp2 = badResp2
    resp3 <- goodAsyncResp3
  } yield {
    s"$resp1-$resp2-$resp3"
  }

  Await.result(aggregatedInnerBadResp.value, 0 nanos) shouldBe Option("201-None-203")

  /** inner is bad (flatMap)*/
  val aggregatedInnerBadResp2 = for {
    resp1 <- goodAsyncResp1
    resp2 <- OptionT(Future.successful(badResp2))
    resp3 <- goodAsyncResp3
  } yield {
    s"$resp1-$resp2-$resp3"
  }

  Await.result(aggregatedInnerBadResp2.value, 0 nanos) shouldBe None

  /** Outer is bad */
  val badAsyncResp3 = OptionT(Future {
    Option(throw new IllegalArgumentException("not good"))
  })

  val aggregatedOuterBadResp = for {
    resp1 <- goodAsyncResp1
    resp2 = goodResp2
    resp3 <- badAsyncResp3
  } yield {
    s"$resp1-$resp2-$resp3"
  }

  aggregatedOuterBadResp.value.isCompleted shouldBe false

}
