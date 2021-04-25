import java.time.LocalDateTime

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors

object MonitoringStation {
  def apply(dispatcher: ActorRef[DispatcherMessage], nextQueryId: Int = 0, timers: Map[Int, Long] = Map.empty): Behavior[MonitoringStationMessage] = {
    Behaviors.receive {
      case (context, StatusCheckRequest(firstSatId, range, timeout)) =>
        dispatcher ! DispatcherQueryMessage(nextQueryId, firstSatId, range, timeout, context.self)
        MonitoringStation(dispatcher, nextQueryId + 1, timers + (nextQueryId -> System.currentTimeMillis))

      case (context, DispatcherResponseMessage(queryId, resMap, answersPercentage)) =>
        println(
          s"Station ${context.self.path.name}\n" +
            s"Query $queryId\n" +
            s"Response time: ${System.currentTimeMillis - timers(queryId)}ms\n" +
            s"Errors: ${resMap.size}\n" +
            s"$answersPercentage% of status responses received\n" +
            resMap.mkString("\t", "\n\t", "\n")
        )
        MonitoringStation(dispatcher, nextQueryId, timers - queryId)
    }
  }
}
