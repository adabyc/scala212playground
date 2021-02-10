package ab.catz.rezources

object SingleResourceApp extends App {

  import cats.effect.{IO, Resource}

  //  def acquire(path: String): IO[Releasable] = IO(println(s"Acquiring resource @ $path...")) *> IO(Releasable("dummy_path"))
  def acquire(path: String): IO[Releasable] = IO {
    println(s"Acquiring resource @ $path...");
    path
  }.flatMap(path => IO(Releasable(path)))

  def release(r: Releasable): IO[Unit] = IO(println(s"...${r.release()} "))

  val additionalWorkOverResource: Releasable => IO[Releasable] = r =>
    IO(println("...extra work before processing...")) *> IO.pure(r.copy(data = r.data + "?param1"))

  val reportProcessing: Releasable => IO[String] = r =>
    IO(println("...side-effectful work when producing report...")) *> IO(s"Report[$r]")

  val result = Resource.make(acquire("/p1"))(release)

    /** additional work on preparing resource */
    .evalMap(additionalWorkOverResource)

    /** do the main work with acquired resource */
    .use(reportProcessing)

    .unsafeRunSync()

  println(result)
  assert(result == "Report[Releasable(/p1?param1)]")
}
