package Collections
import scala.collection.SortedMap
import scalaz.Scalaz._

object CollectionTransforms extends App {

  case class Cfg(acc:String, ips:String, anything:String)
  case class Dict(ips:Option[String], anything2:String)
  case class Qry(ips:String, acc: String, anything2:String)

  val cfg = List(Cfg("ac1", "ips1", "ble"), Cfg("ac2", "ips2", "ble"), Cfg("ac3", "ips3", "ble"))
  val maps = List(Dict(Option("ips3"), "c"), Dict(Option("ips2"), "b"), Dict(Option("ips1"), "a"))

  val whitelist = cfg.map(x => x.ips -> x.acc).toMap
  val y = for{
    entry <- maps
    ipz <- entry.ips
    ac <- whitelist.get(ipz)
  } yield Qry(ipz, ac, "")

  println(y)

}
