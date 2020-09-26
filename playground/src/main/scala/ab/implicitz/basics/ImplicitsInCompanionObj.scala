package ab.implicitz.basics

object ImplicitsInCompanionObj extends App {

  val usd = Dollar(50)
  val eur1: Euro = usd
  println(eur1)

  val chf = SwissFranc(50)
  /** Will not compile due to type mismatch
    * val eur2: Euro = chf
    */

}

case class SwissFranc(amount: Double)

case class Euro(amount: Double)

object Euro {
  /** This will not help if conversion is from CHF */
  implicit def fromCHF(chf: SwissFranc) = Euro(chf.amount * 0.85)
}

case class Dollar(amount: Double)

object Dollar {
  implicit def toEur(d: Dollar): Euro = Euro(d.amount * 1.5)
}


