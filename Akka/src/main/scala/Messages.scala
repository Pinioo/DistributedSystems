import akka.actor.typed.ActorRef

////
sealed trait MonitoringStationMessage

case class StatusCheckRequest(firstSatId: Int,
                              range: Int,
                              timeout: Long) extends MonitoringStationMessage

case class DispatcherResponseMessage(queryId: Int,
                                     errorMap: Map[Int, SatelliteAPI.Status.Value],
                                     answersPercentage: Double) extends MonitoringStationMessage

////
sealed trait DispatcherMessage

case class DispatcherQueryMessage(queryId: Int,
                                  firstSatId: Int,
                                  range: Int,
                                  timeout: Long,
                                  sender: ActorRef[MonitoringStationMessage]) extends DispatcherMessage

case class SatelliteResponseMessage(status: SatelliteAPI.Status.Value) extends DispatcherMessage

////
sealed trait SatelliteMessage

case class SatelliteQueryMessage(sender: ActorRef[DispatcherMessage]) extends SatelliteMessage
