import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

object Satellite {
  def apply(satelliteId: Int): Behavior[SatelliteQueryMessage] = {
    Behaviors.receive {
      case (_, SatelliteQueryMessage(sender)) =>
        sender ! SatelliteResponseMessage(SatelliteAPI.getStatus(satelliteId))
        Behaviors.same
    }
  }
}
