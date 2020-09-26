package ab.hkt.simple

import org.scalatest.Matchers

object ContextBoundApp extends App with Matchers {

  /**
    *
    */

  trait Effect[T] {
    val tVal: T

    def enrich(t: T): Effect[T] = new Effect[T] {
      override val tVal: T = t
    }

    override def toString: String = s"Effect[${tVal.getClass.getSimpleName}]($tVal)"
  }

  implicit val effect: Effect[String] = new Effect[String] {
    override val tVal: String = "init_string"
  }

  def contextBoundPlay[A: Effect](a: A): Effect[A] = {
    val e: Effect[A] = implicitly[Effect[A]]
    e.enrich(a)
  }

  contextBoundPlay("new_val").toString shouldBe "Effect[String](new_val)"

  /**
    *
    */

  trait EffectHK[F[_]] {
    val fVal: F[_]

    def enrich(f: F[_]): EffectHK[F] = new EffectHK[F] {
      override val fVal: F[_] = f
    }

    override def toString: String = s"EffectHK[${fVal.getClass.getSimpleName}]($fVal)"
  }

  implicit val hkEffect = new EffectHK[Option] {
    override val fVal: Option[_] = None
  }

  def higherKindedContextBoundPlay[F[_] : EffectHK](f: F[_]): EffectHK[F] = {
    val e = implicitly[EffectHK[F]]
    e.enrich(f)
  }


  val hke = higherKindedContextBoundPlay(Option("new_val"))
  hke.toString shouldBe "EffectHK[Some](Some(new_val))"


}

