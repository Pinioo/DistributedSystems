import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.AskPattern.Askable
import akka.actor.typed.{ActorRef, Behavior, Scheduler}
import akka.util.Timeout

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration.DurationLong
import scala.language.postfixOps
import scala.util.{Failure, Success}

object Dispatcher {
  def apply(satellites: Map[Int, ActorRef[SatelliteQueryMessage]]): Behavior[DispatcherMessage] = {
      Behaviors.receive {
        case (context, DispatcherQueryMessage(queryId, firstSatId, range, timeout, sender)) =>
          implicit val timeoutImplicit: Timeout = timeout millis
          implicit val scheduler: Scheduler = context.system.scheduler
          implicit val executionContext: ExecutionContext = context.system.executionContext

          val (indices, resultsFutures) = (firstSatId until (firstSatId + range))
            .map(i => (i, satellites(i) ? (ref => SatelliteQueryMessage(ref))))
            .unzip

          val results = Future.sequence(
              resultsFutures
                .map(
                  _.map[Option[SatelliteAPI.Status.Value]]{
                    case m: SatelliteResponseMessage => Some(m.status)
                    case _ => None
                  }
                )
                .map(
                  _.recover{case _ => None}
                )
            )

          results onComplete {
            case Success(res) =>
              val receivedResponses = indices
                .zip(res)
                .filter(_._2.nonEmpty)

              val resMap = receivedResponses
                .map{case (i, statusOpt) => (i, statusOpt.get)}
                .filterNot(_._2 == SatelliteAPI.Status.OK)
                .toMap

              sender ! DispatcherResponseMessage(queryId, resMap, 100.0*receivedResponses.size/res.length)
            case Failure(_) => ()
          }
          Behaviors.same
      }
  }
}
