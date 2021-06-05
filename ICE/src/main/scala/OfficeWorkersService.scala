import Office.{CaseStartedInfo, CaseStatus, CaseStatusEnum, ClientPrx, Country, DriverLicenseCaseData, PassportCaseData, VisaCaseData}

import java.util.concurrent.atomic.AtomicInteger
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Random, Success, Try}

object OfficeWorkersService {
  val minimumEstimatedTime = 2000
  val maximumEstimatedTime = 5000

  def randomProcessing(): Unit = Thread.sleep(Random.between(minimumEstimatedTime, maximumEstimatedTime))

  def trySendingCachedCases(clientId: Int, clientPrx: ClientPrx): Future[Unit] = Future{
    val cases = OfficeWorkersService.awaitingCases.get(clientId)
    cases.filterNot(_.isEmpty).foreach{
      awaitingClientCases =>
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

  var nextCaseId = new AtomicInteger(1)
  var clientProxies: Map[Int, ClientPrx] = Map.empty[Int, ClientPrx]
  var awaitingCases: Map[Int, List[CaseStatus]] = Map.empty[Int, List[CaseStatus]]

  def processPassportCase(passportCaseData: PassportCaseData): CaseStartedInfo = {
    val caseId = nextCaseId.getAndIncrement()
    val thisCaseLogger = caseLogger(caseId)(_)
    thisCaseLogger("Case started")
    Future{
      thisCaseLogger(s"Client: \t${passportCaseData.caseInfo.clientId}")
      thisCaseLogger(s"Payment: \t${passportCaseData.caseInfo.paymentDone}")
      thisCaseLogger(s"First name: \t${passportCaseData.firstname}")
      thisCaseLogger(s"Last name: \t${passportCaseData.lastname}")
      randomProcessing()
      completeCase(
        passportCaseData.caseInfo.clientId,
        new CaseStatus(caseId, passportCaseData.caseInfo.paymentDone match {
          case true => CaseStatusEnum.APPROVED
          case false => CaseStatusEnum.REJECTED
        })
      )
    }
    new CaseStartedInfo(caseId, clientProxies.contains(passportCaseData.caseInfo.clientId))
  }

  def processVisaCase(visaCaseData: VisaCaseData): CaseStartedInfo = {
    val caseId = nextCaseId.getAndIncrement()
    val thisCaseLogger = caseLogger(caseId)(_)
    thisCaseLogger("Case started")
    Future({
      thisCaseLogger(s"Client: \t${visaCaseData.caseInfo.clientId}")
      thisCaseLogger(s"Payment: \t${visaCaseData.caseInfo.paymentDone}")
      thisCaseLogger(s"Passport number: \t${visaCaseData.passportId}")
      thisCaseLogger(s"Country: \t${visaCaseData.country.name}")
      randomProcessing()
      completeCase(
        visaCaseData.caseInfo.clientId,
        new CaseStatus(caseId, (visaCaseData.caseInfo.paymentDone, visaCaseData.country) match {
          case (_, Country.NorthKorea) => CaseStatusEnum.REJECTED
          case (_, Country.Germany) => CaseStatusEnum.APPROVED
          case (true, _) => CaseStatusEnum.APPROVED
        })
      )
    })
    new CaseStartedInfo(caseId, clientProxies.contains(visaCaseData.caseInfo.clientId))
  }

  def processDriverLicenseCase(driverLicenseCaseData: DriverLicenseCaseData): CaseStartedInfo = {
    val caseId = nextCaseId.getAndIncrement()
    val thisCaseLogger = caseLogger(caseId)(_)
    thisCaseLogger("Case started")
    Future({
      thisCaseLogger(s"Client: \t${driverLicenseCaseData.caseInfo.clientId}")
      thisCaseLogger(s"Payment: \t${driverLicenseCaseData.caseInfo.paymentDone}")
      thisCaseLogger(s"First name: \t${driverLicenseCaseData.firstname}")
      thisCaseLogger(s"Last name: \t${driverLicenseCaseData.lastname}")
      thisCaseLogger(s"Exams:")
      driverLicenseCaseData.examResults.zipWithIndex.map{
        case (true, i) => s"Exam $i passed"
        case (_, i) => s"Exam $i failed"
      }.foreach(thisCaseLogger)
      randomProcessing()
      completeCase(
        driverLicenseCaseData.caseInfo.clientId,
        new CaseStatus(caseId, (driverLicenseCaseData.examResults.contains(true), driverLicenseCaseData.caseInfo.paymentDone) match {
          case (true, true) => CaseStatusEnum.APPROVED
          case _ => CaseStatusEnum.REJECTED
        })
      )
    })
    new CaseStartedInfo(caseId, clientProxies.contains(driverLicenseCaseData.caseInfo.clientId))
  }

  def completeCase(clientId: Int, caseStatus: CaseStatus): Unit =
    Try {
      caseLogger(caseStatus.caseId)(s"Case resolved as ${caseStatus.status.name}")
      val clientPrx = clientProxies(clientId)
      caseLogger(caseStatus.caseId)(s"$clientId proxy found")
      clientPrx.sendCaseStatus(caseStatus)
    } match {
      case Failure(_) =>
        awaitingCases += (
          clientId ->
          (caseStatus +: awaitingCases.getOrElse(clientId, List.empty))
        )
        caseLogger(caseStatus.caseId)(s"${caseStatus.caseId} status cached.")
      case Success(_) =>
        caseLogger(caseStatus.caseId)(s"${caseStatus.caseId} status sent successfully.")
    }

  def caseLogger(caseId: Int)(msg: String): Unit =
    println(s"[Case $caseId] $msg")
}
