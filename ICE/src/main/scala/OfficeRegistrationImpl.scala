import Office.{CaseStartedInfo, ClientPrx, DriverLicenseCaseData, OfficeRegistration, PassportCaseData, VisaCaseData}
import com.zeroc.Ice.Current

case class OfficeRegistrationImpl() extends OfficeRegistration {
  override def startPassportCase(passportCaseData: PassportCaseData, current: Current): CaseStartedInfo =
    OfficeWorkersService.processPassportCase(passportCaseData)

  override def startVisaCase(visaCaseData: VisaCaseData, current: Current): CaseStartedInfo =
    OfficeWorkersService.processVisaCase(visaCaseData)

  override def startDriverLicenseCase(driverLicenseCaseData: DriverLicenseCaseData, current: Current): CaseStartedInfo =
    OfficeWorkersService.processDriverLicenseCase(driverLicenseCaseData)

  override def saveClient(clientId: Int, client: ClientPrx, current: Current): Unit = {
    println(s"Client ${clientId} connected")
    val clientPrx = client.ice_fixed(current.con)
    OfficeWorkersService.clientProxies += (clientId -> clientPrx)
    OfficeWorkersService.trySendingCachedCases(clientId, clientPrx)
  }
}
