package ab.catz.tut

import cats.effect.{IO, Resource}
import java.io.{File, FileWriter}

import scala.io.{BufferedSource, Source}

// TODO: error handling
object CopyFileSyncTut extends App {

  val src = new File(getClass.getClassLoader.getResource("input.txt").getFile)
  val dst = new File(getClass.getClassLoader.getResource("output.txt").getFile)

  val linesCount = copy(src, dst)
    .unsafeRunSync()
  println(linesCount)

  def copy(origin: File, destination: File): IO[Long] = {
    val readerWriter = for {
      reader <- getReader(origin)
      writer <- getWriter(destination)
    } yield {
      (reader, writer)
    }

    readerWriter.use {
      case (reader, writer) => doCopy(reader, writer)
    }
  }

  def getReader(origin: File): Resource[IO, BufferedSource] =
    Resource
      .make[IO, BufferedSource](IO(Source.fromFile(origin)))(r => IO(r.close()))

  def getWriter(destination: File): Resource[IO, FileWriter] =
    Resource
      .make[IO, FileWriter](IO(new FileWriter(destination)))(r => IO(r.close()))

  def doCopy(src: BufferedSource, dst: FileWriter): IO[Long] = IO {
    var counter = 0L
    src
      .getLines()
      .foreach { l =>
        dst.write(l)
        counter = counter + 1
      }
    counter
  }


}
