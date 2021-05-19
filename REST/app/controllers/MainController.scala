package controllers

import akka.http.scaladsl.model.DateTime
import play.api.data.Form
import play.api.data.Forms.{boolean, mapping, seq, text}
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
  case class CurrencyRequest(base: String, symbols: Seq[String])
  case class CurrencyInfo(success: Boolean, base: String, rates: Map[String, Map[String, Double]])

  implicit val ec: ExecutionContext = cc.executionContext

  implicit val currencyInfoFromJson: Reads[CurrencyInfo] = (
    (JsPath \ "success").read[Boolean] and
      (JsPath \ "base").read[String] and
      (JsPath \ "rates").read[Map[String, Map[String, Double]]]
  )(CurrencyInfo.apply _)

  val requestForm: Form[CurrencyRequest] = Form(
    mapping(
      "base" -> text,
      "symbols" -> seq(text)
    )(CurrencyRequest.apply)(CurrencyRequest.unapply)
  )

  val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  def externalApiCalls(r: CurrencyRequest): Seq[String] =
    r.symbols.map(symbol => "https://api.exchangerate.host/timeseries" +
      s"?start_date=${LocalDate.now.minusDays(30).format(dateFormat)}" +
      s"&end_date=${LocalDate.now.format(dateFormat)}" +
      s"&base=${r.base}" +
      s"&symbols=$symbol")

  def count: Action[AnyContent] = Action { implicit request =>
    requestForm.bindFromRequest.fold(
      e => BadRequest(views.html.main("invalid form")),
      r => Try(Await.result(
          Future.sequence(
            externalApiCalls(r)
              .map(url =>
                ws.url(url).get()
              )
          ),
          5.seconds
      )) match {
        case Success(httpResponses) =>
          val responses = httpResponses.map(r => r.json.validate[CurrencyInfo].get)
          Ok(views.html.main(responses.mkString("\n")))
        case Failure(exception) =>
          BadRequest(views.html.main(exception.toString))
      }
    )
  }
}
