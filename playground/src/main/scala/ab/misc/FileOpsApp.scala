package ab.misc

import java.io.{BufferedReader, BufferedWriter, DataOutputStream, FileOutputStream, FileReader, FileWriter, PrintWriter}

import scala.io.BufferedSource
import scala.reflect.io.File
import scala.util.Random

/**
  * WRITING TO FILE
  *
  * A) Text file
  * 1. FileWriter - basic convenience class for writing to text file
  * 2. PrintWriter - printf-like API for writing to text file
  *
  * B) Writing primitives
  * 1. DataOutputStream - class helps us to write Java primitive data types efficiently. Eg. double is persisted as binary data
  * rather than character, which takes less space.
  *
  * C) Writing data efficiently
  *
  * READING FROM FILE
  * Contrary to writing, Scala does provide native API for reading files.
  * 1.
  */
object FileOpsApp extends App {

  val file1Path = getClass.getClassLoader.getResource("file1.txt").getFile
  val file1 = File(file1Path)


  /** FileWriter */
  val file2Path = getClass.getClassLoader.getResource("file2.txt").getFile
  println(s"resources file path: $file2Path")
  val fileWriter = new FileWriter(file2Path)
  fileWriter.write("someTest")
  fileWriter.close()

  /** PrintWriter */
  val file3Path = getClass.getClassLoader.getResource("file3.txt").getFile
  println(s"resources file path: $file3Path")
  val printWriter = new PrintWriter(file3Path)
  printWriter.printf("text %s with formatting", "value-text")
  val height = 1.9d
  val name = "James"
  printWriter.println(f"$name%s is $height%2.2f meters tall") // f"" is a Scala's interpolation
  printWriter.close()

  /** DataOutputStream */
  val pi = Math.PI
  val piFilePath = getClass.getClassLoader.getResource("pi.txt").getFile
  val dataOutputStream = new DataOutputStream(new FileOutputStream(piFilePath))

  dataOutputStream.writeDouble(pi)
  assert(dataOutputStream.size() == 8)
  dataOutputStream.close()
  assert(pi.toString.getBytes.length == 17)

  /** Buffered writes */
  val bufferedFilePath = getClass.getClassLoader.getResource("buffered.txt").getFile
  val bufferedPrintWriter = new BufferedWriter(new PrintWriter(bufferedFilePath))

  val random = new Random()
  for (_ <- 1 to 10000) {
    bufferedPrintWriter.write(random.nextDouble().toString)
  }
  bufferedPrintWriter.close()


  /** Reading from BufferedSource via Iterator */
  val bufferedSource: BufferedSource = scala.io.Source.fromFile(bufferedFilePath)
  val linesIterator: Iterator[String] = bufferedSource.getLines()
  linesIterator.take(4).foreach { line => println(line) }
  // NOTE: `BufferedSource` should not be closed immediately.
  bufferedSource.close()

  /** Read file like Java */
  val fileReader = new BufferedReader(new FileReader(bufferedFilePath))
  def handleRead(line : String, linesCounter: Int) : Unit = {
    val newLine = fileReader.readLine()
    println(s"Java is reading lines $newLine")
    if(newLine != null && linesCounter > 0)  // if there are more lines to read
      handleRead(newLine, linesCounter - 1)
  }
  handleRead(fileReader.readLine(), 4)
  fileReader.close()
}

