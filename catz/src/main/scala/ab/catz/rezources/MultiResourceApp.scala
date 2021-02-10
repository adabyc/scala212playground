package ab.catz.rezources

object MultiResourceApp extends App {

  import cats.effect.{IO, Resource}

  def mkResource(path: String): Resource[IO, Releasable] = {
    val acquire = IO(println(s"Acquiring $path...")) *> IO.pure(Releasable(path))

    def release(r: Releasable): IO[Unit] = IO(println(s"...${r.release()}"))

    Resource.make(acquire)(release)
  }

  val combinedResourcesAsResourceOfTuple: Resource[IO, (Releasable, Releasable)] = for {
    outer <- mkResource("outer")
    inner <- mkResource("inner")
  } yield (outer, inner)

  val result = combinedResourcesAsResourceOfTuple
    .use { case (a, b) => IO(println(s"Using $a and $b")) *> IO.pure(s"[$a/$b]") }
    .unsafeRunSync()

  println(result)
  assert(result == "[Releasable(outer)/Releasable(inner)]")
}
