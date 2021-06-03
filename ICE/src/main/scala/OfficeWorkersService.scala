import Office.{CaseStatus, CaseStatusEnum, ClientPrx, PassportCaseData}

import java.util.concurrent.ConcurrentHashMap
import scala.collection.concurrent
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.jdk.CollectionConverters.ConcurrentMapHasAsScala
import scala.util.{Random, Try}

object OfficeWorkersService {
  val clientProxies: concurrent.Map[Int, ClientPrx] = new ConcurrentHashMap[Int, ClientPrx]().asScala
  val awaitingCases: concurrent.Map[Int, List[CaseStatus]] = new ConcurrentHashMap[Int, List[CaseStatus]]().asScala

  def processPassportCase(passportCaseData: PassportCaseData): Int = {
    val estimatedTime = Random.between(5000, 15000)
    Future({
      Thread.sleep(estimatedTime)
      completeCase(
        passportCaseData.caseInfo.clientId,
        new CaseStatus(passportCaseData.caseInfo.caseId, passportCaseData.caseInfo.paymentDone match {
          case true => CaseStatusEnum.APPROVED
          case false => CaseStatusEnum.REJECTED
        })
      )
    })
    estimatedTime
  }

  def completeCase(clientId: Int, caseStatus: CaseStatus): Unit =
    clientProxies.get(clientId).foreach {
      clientPrx =>
        Try {
          clientPrx.sendCaseStatus(caseStatus)
        } match {
          case _ =>
            awaitingCases.put(
              clientId,
              caseStatus +: awaitingCases.getOrElse(clientId, List.empty)
            )
        }
    }
}
