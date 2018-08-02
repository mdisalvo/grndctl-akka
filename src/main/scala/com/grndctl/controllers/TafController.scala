package com.grndctl.controllers

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.StatusCodes._
import com.grndctl.misc.HttpResponseSupport
import com.grndctl.model.taf.{TAF, TimeType}
import com.grndctl.services.TafSvc
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class TafController(tafSvc: TafSvc)
                   (implicit ec: ExecutionContext)
  extends HttpResponseSupport with LazyLogging {

  val route: Route =
    pathPrefix("taf") {
      pathPrefix(Segment) { stationId =>
        parameters('hrsBefore ? "2.0", 'timeType ? TimeType.VALID.valueOf()) { (hrsBefore, timeType) =>
          validate(TimeType.fromString(timeType).isDefined, s"Invalid TimeType $timeType.") {
            getTafs(stationId, hrsBefore.toDouble, TimeType.fromString(timeType).get)
          }
        }
      }
    }

  def getTafs(stationId: String, hrsBefore: Double, timeType: TimeType): Route = {
    onSuccess(tafSvc.getTafs(stationId, hrsBefore, timeType)) { response =>
      completeResponse(response)
    }
  }

  private def completeResponse(response: Seq[TAF]): Route = {
    logger.debug(s"Response collection size: ${response.size}")
    if (response.isEmpty) {
      extractRequest { request =>
        complete(NotFound, s"Station with ICAO id ${request.uri.toString().split("/").last} Not Found")
      }
    }
    else complete(seqToHttpResponse(response))
  }

}
