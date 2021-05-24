package controllers

import play.api.data.Form
import play.api.data.Forms.{mapping, seq, text}
import play.api.libs.ws.WSClient
import play.api.libs.json._
import play.api.libs.functional.syntax._

import javax.inject._
import play.api.mvc._

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Success, Try}

@Singleton
class MainController @Inject()(ws: WSClient, cc: ControllerComponents) extends AbstractController(cc) {
  val tab = "&emsp;"

  case class CurrencyRequest(base: String, symbols: Seq[String])
  case class CurrencyInfo(base: String, rates: Map[LocalDate, Map[String, Double]]) {
    def extractedRates: Seq[(LocalDate, Double)] = rates
      .map{
        case (date, rateMap) => (date, rateMap(rateMap.keySet.head))
      }.toList
      .sortBy(_._1)

    def changes: Seq[(LocalDate, Double)] = extractedRates
      .zip(extractedRates.tail)
      .map{
        case ((_, before), (date, after)) => (date, after / before)
      }

    lazy val current: (LocalDate, Double) = extractedRates.last

    lazy val minimum: (LocalDate, Double) = extractedRates.minBy(_._2)
    lazy val maximum: (LocalDate, Double) = extractedRates.maxBy(_._2)

    lazy val minimumChange: (LocalDate, Double) = changes.minBy(_._2)
    lazy val maximumChange: (LocalDate, Double) = changes.maxBy(_._2)

    override def toString: String =
      f"${tab}Current: ${current._2}%.4f @ ${current._1}<br />\n" +
      f"${tab}Minimum: ${minimum._2}%.4f @ ${minimum._1}<br />\n" +
      f"${tab}Maximum: ${maximum._2}%.4f @ ${maximum._1}<br />\n" +
      f"${tab}Minimum change: ${minimumChange._2*100}%.2f %% @ ${minimumChange._1}<br />\n" +
      f"${tab}Maximum change: ${maximumChange._2*100}%.2f %% @ ${maximumChange._1}<br />\n"
  }

  val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  implicit val ec: ExecutionContext = cc.executionContext

  implicit val currencyInfoFromJson: Reads[CurrencyInfo] = (
    (JsPath \ "base").read[String] and
    (JsPath \ "rates").read[Map[String, Map[String, Double]]]
      .map(_.map{
        case k -> v => LocalDate.parse(k, dateFormat) -> v
      })
  )(CurrencyInfo.apply _)

  val requestForm: Form[CurrencyRequest] = Form(
    mapping(
      "base" -> text,
      "symbols" -> seq(text)
    )(CurrencyRequest.apply)(CurrencyRequest.unapply)
  )

  def externalApiCalls(r: CurrencyRequest): Seq[(String, String, String)] =
    r.symbols.map(symbol => (r.base, symbol, "https://api.exchangerate.host/timeseries" +
      s"?start_date=${LocalDate.now.minusDays(30).format(dateFormat)}" +
      s"&end_date=${LocalDate.now.format(dateFormat)}" +
      s"&base=${r.base}" +
      s"&symbols=$symbol"))

  def getCurrencyInfo: Action[AnyContent] = Action { implicit request =>
    requestForm.bindFromRequest.fold(
      _ => BadRequest(views.html.main("invalid form")),
      r => Try(Await.result(
          Future.sequence(
            externalApiCalls(r)
              .map { case (base, currency, url) =>
                ws
                  .url(url)
                  .get()
                  .map {
                    r =>
                      r.json.validate[CurrencyInfo].fold(
                        _ => s"${tab}Received invalid JSON",
                        currencyInfo => currencyInfo.toString
                      )
                  }
                  .recover {
                    case _ => s"${tab}Problem occured"
                  }
                  .map(s"$base -> $currency<br />" + _)
              }
          ),
          5.seconds
      )) match {
        case Success(responses) =>
          Ok(views.html.main(responses.mkString("<br />")))
        case Failure(exception) =>
          BadRequest(views.html.main(exception.toString))
      }
    )
  }
}
