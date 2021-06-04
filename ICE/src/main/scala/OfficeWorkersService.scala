import Office.{CaseStatus, CaseStatusEnum, ClientPrx, Country, DriverLicenseCaseData, PassportCaseData, VisaCaseData}

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import scala.collection.concurrent
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.jdk.CollectionConverters.ConcurrentMapHasAsScala
import scala.util.{Failure, Random, Try}

object OfficeWorkersService {
  var nextCaseId = new AtomicInteger(1)
  var clientProxies: Map[Int, ClientPrx] = Map.empty[Int, ClientPrx]
  var awaitingCases: Map[Int, List[CaseStatus]] = Map.empty[Int, List[CaseStatus]]

  def processPassportCase(passportCaseData: PassportCaseData): Int = {
    val caseId = nextCaseId.getAndIncrement()
    Future({
      Thread.sleep(Random.between(2000, 4000))
      completeCase(
        passportCaseData.caseInfo.clientId,
        new CaseStatus(caseId, passportCaseData.caseInfo.paymentDone match {
          case true => CaseStatusEnum.APPROVED
          case false => CaseStatusEnum.REJECTED
        })
      )
    })
    caseId
  }

  def processVisaCase(visaCaseData: VisaCaseData): Int = {
    val caseId = nextCaseId.getAndIncrement()
    Future({
      Thread.sleep(Random.between(2000, 4000))
      completeCase(
        visaCaseData.caseInfo.clientId,
        new CaseStatus(caseId, (visaCaseData.caseInfo.paymentDone, visaCaseData.country) match {
          case (_, Country.NorthKorea) => CaseStatusEnum.REJECTED
          case (_, Country.Germany) => CaseStatusEnum.APPROVED
          case (true, _) => CaseStatusEnum.APPROVED
        })
      )
    })
    caseId
  }

  def processDriverLicenseCase(driverLicenseCaseData: DriverLicenseCaseData): Int = {
    val caseId = nextCaseId.getAndIncrement()
    Future({
      Thread.sleep(Random.between(2000, 4000))
      completeCase(
        driverLicenseCaseData.caseInfo.clientId,
        new CaseStatus(caseId, driverLicenseCaseData.caseInfo.paymentDone match {
          case true => CaseStatusEnum.APPROVED
          case false => CaseStatusEnum.REJECTED
        })
      )
    })
    caseId
  }

  def completeCase(clientId: Int, caseStatus: CaseStatus): Unit =
    clientProxies.get(clientId).foreach {
      clientPrx =>
        println(s"${clientId} proxy found")
        Try {
          clientPrx.sendCaseStatus(caseStatus)
          println(s"${caseStatus.caseId} status sent successfully.")
        } match {
          case Failure(e) =>
            awaitingCases += (
              clientId ->
              (caseStatus +: awaitingCases.getOrElse(clientId, List.empty))
            )
            println(s"${caseStatus.caseId} status cached.")
        }
    }
}
