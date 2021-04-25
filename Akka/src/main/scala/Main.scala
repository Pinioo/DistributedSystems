import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, DispatcherSelector}

import scala.io.StdIn
import scala.util.Random

case object Main extends App {
  val actorSystem = ActorSystem(Behaviors.empty, "MainSystem")
  val customDispatcher = DispatcherSelector.fromConfig("my-dispatcher")

  val satelliteCreator = (i: Int) =>
    i -> actorSystem.systemActorOf(Satellite(i), s"Satellite-$i", customDispatcher)
  val satellites = (100 to 199)
    .map(satelliteCreator).toMap
  val dispatcher = actorSystem
    .systemActorOf(Dispatcher(satellites), "Dispatcher", customDispatcher)

  val monitoringStationCreator = (name: String) =>
    actorSystem.systemActorOf(MonitoringStation(dispatcher), name, customDispatcher)
  val monitoringStationsCount = 3
  val monitoringStations = Seq(
    "Station-e4",
    "Station-d4",
    "Station-Nf3"
  ).map(monitoringStationCreator)

  Iterator
    .continually(StdIn.readLine)
    .takeWhile(_ != "q")
    .foreach{
      case "t" =>
        for(station <- monitoringStations ++ monitoringStations){
          station ! StatusCheckRequest(Random.between(100, 150), 50, 300)
        }
      case _ => ()
    }
}
