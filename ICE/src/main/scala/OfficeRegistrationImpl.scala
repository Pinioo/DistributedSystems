import Office.{ClientPrx, DriverLicenseCaseData, OfficeRegistration, PassportCaseData, VisaCaseData}
import com.zeroc.Ice.Current

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

case class OfficeRegistrationImpl() extends OfficeRegistration {
  override def startPassportCase(passportCaseData: PassportCaseData, current: Current): Int =
    OfficeWorkersService.processPassportCase(passportCaseData)

  override def startVisaCase(visaCaseData: VisaCaseData, current: Current): Int =
    OfficeWorkersService.processVisaCase(visaCaseData)

  override def startDriverLicenseCase(driverLicenseCaseData: DriverLicenseCaseData, current: Current): Int =
    OfficeWorkersService.processDriverLicenseCase(driverLicenseCaseData)

  override def saveClient(clientId: Int, client: ClientPrx, current: Current): Unit = {
    println(s"Client ${clientId} connected")
    val clientPrx = client.ice_fixed(current.con)
    OfficeWorkersService.clientProxies += (clientId -> clientPrx)

    val cases = OfficeWorkersService.awaitingCases.get(clientId)
    cases.filterNot(_.isEmpty).foreach{
      awaitingClientCases =>
        Future {
          Try {
            clientPrx.sendCachedCaseStatuses(awaitingClientCases.toArray)
          } match {
            case Success(_) =>
              println("Cached cases successfully sent")
              val emptiedList = OfficeWorkersService.awaitingCases.getOrElse(clientId, List.empty).diff(awaitingClientCases)
              OfficeWorkersService.awaitingCases += (clientId -> emptiedList)

            case Failure(_) =>
              println("Cached cases could not be sent")
          }
        }
    }
  }
}
