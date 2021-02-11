package ab.catz.rezources

import scala.collection.JavaConverters._

// TODO: WIP
object FileResourceApp extends App {

  import java.io._
  import cats.effect._

  def readAllLines(bufferedReader: BufferedReader, blocker: Blocker)(implicit cs: ContextShift[IO]): IO[List[String]] =
    blocker.delay[IO, List[String]] {
      bufferedReader.lines().iterator().asScala.toList
    }

  def reader(file: File, blocker: Blocker)(implicit cs: ContextShift[IO]): Resource[IO, BufferedReader] =
    Resource.fromAutoCloseableBlocking(blocker)(IO {
      new BufferedReader(new FileReader(file))
    }
    )

  def readLinesFromFile(file: File, blocker: Blocker)(implicit cs: ContextShift[IO]): IO[List[String]] = {
    reader(file, blocker).use(br => readAllLines(br, blocker))
  }

  val filePath: String = getClass.getClassLoader.getResource("input.txt").getFile
//    readLinesFromFile(new File(filePath))
}
