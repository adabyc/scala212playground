package ab.catz

import cats.data._
import cats.implicits._
import org.scalatest.matchers.should.Matchers

import scala.util.{Success, Try}

/**
  * OptionT[F[_], A] is a light wrapper on an F[Option[A]]
  */
object OptionTApp extends App with Matchers {

  val goodResp: Try[Option[String]] = Try(Option("200"))
  val badResp: Try[Option[String]] = Try(Option("500"))

  /**
    * Scenario - async response has to be mapped to some other value
    */

  /** OptionT allows to map/flatMap etc over inner Effect which is Option */
  val goodOptT: OptionT[Try, String] = OptionT(goodResp)
  val goodDataT: OptionT[Try, Int] = goodOptT.filter(_ == "200").map(respStr => respStr.toInt)
  goodDataT.value shouldBe Success(Option(200))

  val badOptT = OptionT(badResp)
  val badResultT: OptionT[Try, String] = badOptT.filter(_ == "200")
  badResultT.value shouldBe Success(None)
  val badDataT = badResultT.map((x: String) => x.toInt)
  badDataT.value shouldBe Success(None)

    /**  Without Monad transformer (OptionT) explicit version is as follows */
  val goodResult: Try[Option[String]] = goodResp.map(maybeOk => maybeOk.filter(_ == "200"))
  goodResult shouldBe Success(Some("200"))

  val goodData: Try[Option[Int]] = goodResult.map(someRes => someRes.map(_ => 200))
  goodData shouldBe Success(Option(200))

  // bad
  val badResult = badResp.map(maybeOk => maybeOk.filter(_ == "200"))
  badResult shouldBe Success(None)




}
