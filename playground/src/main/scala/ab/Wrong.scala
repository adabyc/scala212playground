package ab

sealed trait Wrong extends Product with Serializable
final case class WrongWithMsg(errMsg: String) extends Wrong
final case class WrongWithEx(errMsg: String, th: Throwable) extends Wrong


