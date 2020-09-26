package ab.implicitz.basics

/**
  * https://docs.scala-lang.org/tutorials/FAQ/finding-implicits.html
  */

object ImplicitsConveringFuncArg extends App {

  import ImplicitzImpl.message2Event

  /**
    * Implicit conversion
    */
  def logEvent(ev: Event) = println("handled event: " + ev.toString)

  logEvent(Message(1, "MessageToEvent"))
}

object ImplicitsConvertingObjetWithMethodApp extends App {

  import ImplicitzImpl.extUser2Person

  /**
    * Conversion of the receiver
    */
  val extUser = new ExternalUser("some_user")
  println(extUser.getFullName)

}

object ImplicitsAsRichWrappersApp extends App {

  import ImplicitzImpl.ComplexMaker

  /**
    * Implicit classes => Rich Wrappers
    */
  val complexNumber = 2 j 3
  println(complexNumber)
}

object ImplicitArgs extends App {

  import ImplicitzImpl.{defaultContext, defaultProfile}

  /**
    * Implicit parameters 21.5
    */
  def logData(value: String)(implicit context: Ctx, profile: Profile) =
    println(s"$value in ${context.data} context and ${profile.name} profile")

  // FIXME
//  logData("currentData")

}

object ImplicitzImpl {

  implicit def message2Event(m: Message): Event = Event(m.id.toString, m.data)

  implicit def extUser2Person(eu: ExternalUser): Person = new Person("no_name", eu.userName)

  implicit class ComplexMaker(re: Int) {
    def j(im: Int) = ComplexNum(re, im)
  }

  implicit val defaultContext = new Ctx("default_ctx_01")
  implicit val defaultProfile = Profile("default_prof_01")
}