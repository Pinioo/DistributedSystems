import scala.concurrent.Future
import scala.jdk.OptionConverters.RichOptional
import scala.jdk.StreamConverters.StreamHasToScala
import scala.sys.process._
import org.apache.zookeeper
import org.apache.zookeeper.AddWatchMode
import org.apache.zookeeper.{WatchedEvent, Watcher, ZooKeeper}

import scala.io.StdIn

object Main extends App {
  val connectionString = "127.0.0.1:2181"
  val zk = new ZooKeeper(connectionString, 10000, null)
  val externalAppPath = args(0)
  val customWatcher = ZkObserver(zk, "/z", externalAppPath)
  zk.addWatch(
    "/z",
    customWatcher,
    AddWatchMode.PERSISTENT_RECURSIVE
  )
  Iterator
    .continually(StdIn.readLine())
    .takeWhile(_ != "q")
    .foreach{
      case "t" =>
        customWatcher.sem.acquire()
        customWatcher.currentTree.foreach(_.printTree())
        customWatcher.sem.release()
      case "s" =>
        customWatcher.sem.acquire()
        customWatcher.currentTree.foreach(
          t => println(s"Tree size: ${t.size}")
        )
        customWatcher.sem.release()
      case _ => ()
    }
  zk.close()
}