package ab.oop

import org.scalatest.Matchers

object AOPApp extends App with Matchers {

  val reader = new ReaderImpl() with ReaderLogger
  reader.read shouldBe "data"
  ReaderLogger.log shouldBe "logged"
}

trait Reader {
  def read(): String
}

class ReaderImpl extends Reader {
  override def read(): String = {
    Thread.sleep(100)
    "data"
  }
}

trait ReaderLogger extends Reader {

  import ReaderLogger._

  abstract override def read(): String = {
    log = "logged"
    super.read()
  }
}

object ReaderLogger {

  var log: String = ""
}


