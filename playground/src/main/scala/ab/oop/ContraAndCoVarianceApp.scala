package ab.oop

trait Stack[+T] {
  def head: Option[T]
  def enqueue[U >: T](x: U): Stack[U]
  def dequeue(): Stack[T]
}

trait StackApp {

  val st1 = createStack("one")
  println(st1.head)

  val st2 = st1.enqueue("two")
  println(st2.head)
  println(st2.dequeue().head)

  def createStack[T](h:T) : Stack[T]
}

object ContraAndCoVariantApp_v1 extends App with StackApp {
  println("Version 1")

  class FuncStack[+T](l: List[T]) extends Stack[T] {
    def head = l.headOption
    def enqueue[U >: T](h: U): Stack[U] = new FuncStack(h :: l)
    def dequeue(): Stack[T] = new FuncStack(l.tail)
  }

  override def createStack[T](h: T): Stack[T] = new FuncStack[T](List(h))
}

object ContraAndCoVariantApp_v2 extends App with StackApp {
  println("Version 2")

  class EmptyStack[T] extends Stack[T] {
    def head = None
    def enqueue[U >: T](h: U): Stack[U] = createStack(h)
    def dequeue() = new EmptyStack()
  }

  class FullStack[+T](h: T, s: Stack[T]) extends Stack[T] {
    def head = Option(h)
    def enqueue[U >: T](h: U): Stack[U] = new FullStack[U](h, this)
    def dequeue() = s
  }

  override def createStack[T](h: T): Stack[T] = new FullStack[T](h, new EmptyStack[T]())
}
