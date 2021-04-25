import org.apache.zookeeper.Watcher.Event.EventType.{NodeCreated, NodeDeleted}
import org.apache.zookeeper.{WatchedEvent, Watcher, ZooKeeper}

import java.util.concurrent.Semaphore
import scala.jdk.CollectionConverters.ListHasAsScala

case class ZkObserver(zk: ZooKeeper, rootNodePath: String, externalAppPath: String) extends Watcher {
  var process: Option[Process] = None
  var currentTree: Option[TreeNode[String]] = None
  val sem: Semaphore = new Semaphore(1)

  if(zk.exists(rootNodePath, false) != null){
    sem.acquire()
    currentTree = zookeeperDfs(rootNodePath).lift
    sem.release()
  }

  def onRootNodeCreated(): Unit = {
    process = Option(Runtime.getRuntime.exec(externalAppPath))
    sem.acquire()
    currentTree = zookeeperDfs(rootNodePath).lift
    sem.release()
  }

  def onRootNodeDeleted(): Unit = {
    process.foreach(_.destroy())
    process = None
    sem.acquire()
    currentTree = None
    sem.release()
  }

  def onDescendingNodeCreated(): Unit = {
    sem.acquire()
    currentTree = zookeeperDfs(rootNodePath).lift
    println(s"/z descendants: ${currentTree.get.size - 1}")
    sem.release()
  }

  def onDescendingNodeDeleted(): Unit = ()

  override def process(event: WatchedEvent): Unit = {
    (event.getPath, event.getType) match {
      case (this.rootNodePath, NodeCreated) => onRootNodeCreated()
      case (this.rootNodePath, NodeDeleted) => onRootNodeDeleted()
      case (_, NodeCreated) => onDescendingNodeCreated()
      case (_, NodeDeleted) => onDescendingNodeDeleted()
      case _ => ()
    }
  }

  def zookeeperDfs(path: String): TreeNode[String] = {
    TreeNode(
      path,
      zk
        .getChildren(s"$path", false).asScala.toList
        .map(childPath => s"$path/$childPath")
        .map(zookeeperDfs)
    )
  }
}
