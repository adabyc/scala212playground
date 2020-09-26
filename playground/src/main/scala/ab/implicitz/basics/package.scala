package ab.implicitz

package object basics {

  case class Message(id: Int, data: String)

  case class Event(evId: String, payload: String)

  class ExternalUser(val userName: String) {}

  class Person(firstName: String, lastName: String) {
    def getFullName: String = s"$firstName,$lastName"
  }

  case class ComplexNum(re: Int, im: Int)

  class Ctx(val data: String) {}

  case class Profile(name: String)


}
