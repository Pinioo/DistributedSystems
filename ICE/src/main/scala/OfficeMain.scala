import com.zeroc.Ice.Util

object OfficeMain extends App {
  try {
    val communicator = Util.initialize(args)
    val adapter = communicator.createObjectAdapterWithEndpoints("OfficeAdapter", "default -p 10101")
    val registrationObject = OfficeRegistrationImpl()
    adapter.add(registrationObject, Util.stringToIdentity("OfficeRegistration"))
    adapter.activate()
    communicator.waitForShutdown()
  } catch {
    case e: Throwable => e.printStackTrace()
  }
}
