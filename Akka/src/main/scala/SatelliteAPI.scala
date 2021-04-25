import scala.util.Random

object SatelliteAPI {
  object Status extends Enumeration {
    type Status = Value
    val OK, BATTERY_LOW, PROPULSION_ERROR, NAVIGATION_ERROR = Value
  }

  def getStatus(satelliteIndex: Int): Status.Value = {
    val rand = new Random
    try Thread.sleep(100 + rand.nextInt(400))
    catch {
      case e: InterruptedException =>
        e.printStackTrace()
    }
    val p = Random.nextDouble

    if (p < 0.8) Status.OK
    else if (p < 0.9) Status.BATTERY_LOW
    else if (p < 0.95) Status.NAVIGATION_ERROR
    else Status.PROPULSION_ERROR
  }
}

