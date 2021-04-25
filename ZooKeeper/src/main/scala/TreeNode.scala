import scala.collection.mutable

case class TreeNode[T](value: T, children: List[TreeNode[T]] = Nil) {
  def printTree(levels: Seq[Boolean] = Seq.empty): Unit = {
    levels match {
      case Nil => println(value.toString)
      case _ =>
        val preString = levels.init.map{
          case true => "|\t"
          case false => "\t"
        }.mkString
        println(s"$preString|")
        println(s"$preString+---${value.toString}")
    }
    children match {
      case c :: cs =>
        cs.foreach(_.printTree(levels :+ true))
        c.printTree(levels :+ false)
      case _ => ()
    }
  }

  def size: Int = 1 + children.map(_.size).sum
  def lift: Option[TreeNode[T]] = Option(this)
}
