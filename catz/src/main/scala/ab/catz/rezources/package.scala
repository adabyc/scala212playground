package ab.catz

package object rezources {

  case class Releasable(data: String) {
    def release(): String = s"releasing: $data"
  }

}
