package ab.catz

import java.util.concurrent.ConcurrentHashMap

import cats.effect.IO

import scala.collection.convert.ImplicitConversions.`map AsScala`
import java.util.concurrent.atomic.AtomicInteger

object IoSynchApp extends App with AppOps {

  val io: IO[Unit] = for {
    d <- IO(fetchData())
    p <- IO(processData(d))
    _ <- IO(pushData(p))
  } yield {
    ()
  }

  io.unsafeRunSync()

  println(DB.getAll)

  def fetchData(): Int = {
    Iterator
      .continually(counter.getAndIncrement())
      .map(x => {
        Thread.sleep(500)
        x
      }).next()
  }

  def processData(data: Int): String = {
    logThTime(s"processing data $data...")
    //    Thread.sleep(100)
    val result = data + "-data"
    logThTime(s"processed $result")
    result
  }

  def pushData(data: String) = {
    DB.insert(data)
    logThTime(s"persisted $data")
  }
}

object DB {

  private val pkCounter = new AtomicInteger(1)
  private val records: ConcurrentHashMap[Int, String] = new ConcurrentHashMap[Int, String]()

  def insert(record: String): String = records.put(pkCounter.getAndIncrement(), record)
  def getAll: List[String] = records.toMap.keys.toList.sorted.map(id => s"$id-${records(id)}")
}