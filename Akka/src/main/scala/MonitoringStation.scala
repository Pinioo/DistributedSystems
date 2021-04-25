import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors

object MonitoringStation {
  def apply(dispatcher: ActorRef[DispatcherMessage], nextQueryId: Int = 0): Behavior[MonitoringStationMessage] = {
    Behaviors.receive {
      case (context, StatusCheckRequest(firstSatId, range, timeout)) =>
        dispatcher ! DispatcherQueryMessage(nextQueryId, firstSatId, range, timeout, context.self)
        MonitoringStation(dispatcher, nextQueryId + 1)

      case (context, DispatcherResponseMessage(queryId, resMap, answersPercentage)) =>
        println(
          s"Station ${context.self.path.name}, Query $queryId\n" +
            s"$answersPercentage% of status responses received\n" +
            resMap.mkString("\t", "\n\t", "\n")
        )
        Behaviors.same
    }
  }
}
