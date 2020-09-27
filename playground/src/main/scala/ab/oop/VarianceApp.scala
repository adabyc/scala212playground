package ab.oop

/**
  * COMMENTS:
  * In a purely functional world, many types are naturally covariant.
  * Arrays are nonvariant.
  *
  */
object VarianceApp extends App {


  /** COVARIANCE
    * Applies to returned instances, hence naturally fits pure functional structures.
    *
    * As soon as a generic parameter type appears as the type of a method parameter, the containing class or trait
    * may not be covariant in that type parameter.
    * */
  class CovariantGetter[+T](x: T) {
    def getData: T = x
  }

  val containerGet = new CovariantGetter(new Authorized)
  val user1: Authorized = containerGet.getData
  val user2: User = containerGet.getData

  val containerGetBase: CovariantGetter[User] = containerGet
  val user3: User = containerGetBase.getData

  /** will not compile
    * val authUser2: Authorized = containerGetBase.getData
    */

  /** CONTRAVARIANCE
    *
    */
  class ContravariantSetter[-T]() {
    def setData(x: T) = println(s"contravariant argument $x")
  }

  val contravariantSetter = new ContravariantSetter[Authorized]()
  contravariantSetter.setData(new Authorized())
  contravariantSetter.setData(new Admin())
  /** contravariantSetter.setData(new User()) - will not compile */

  val contravariantSetterBase: ContravariantSetter[Admin] = contravariantSetter
  contravariantSetter.setData(new Authorized())
  contravariantSetter.setData(new Admin())
  /** contravariantSetter.setData(new User()) - will not compile */

}


class User()

class Authorized() extends User

class Admin() extends Authorized


