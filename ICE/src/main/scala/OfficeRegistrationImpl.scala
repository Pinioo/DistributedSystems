import Office.{ClientPrx, DriverLicenseCaseData, OfficeRegistration, PassportCaseData, VisaCaseData}
import com.zeroc.Ice.Current

import scala.util.Try

case class OfficeRegistrationImpl() extends OfficeRegistration {
  override def startPassportCase(passportCaseData: PassportCaseData, current: Current): Int =
    OfficeWorkersService.processPassportCase(passportCaseData)

  override def startVisaCase(visaCaseData: VisaCaseData, current: Current): Int = {
    10
  }

  override def startDriverLicenseCase(driverLicenseCaseData: DriverLicenseCaseData, current: Current): Int = {
    10
  }

  override def saveClient(clientId: Int, client: ClientPrx, current: Current): Unit = {
    OfficeWorkersService.clientProxies.put(clientId, client)
    OfficeWorkersService.awaitingCases.remove(clientId) match {
      case Some(awaitingClientCases) =>
        Try {
          client.sendCachedCaseStatuses(awaitingClientCases.toArray)
        } match {
          case _ => OfficeWorkersService.awaitingCases.put(clientId, awaitingClientCases)
        }
    }
  }
}
