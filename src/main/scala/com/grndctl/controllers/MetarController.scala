package com.grndctl.controllers

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.grndctl.misc.HttpResponseSupport
import com.grndctl.model.metar.METAR
import com.grndctl.services.MetarSvc
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext
import scala.util.Try

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class MetarController(metarService: MetarSvc)
                     (implicit ec: ExecutionContext)
  extends HttpResponseSupport with LazyLogging {

  val route: Route =
    pathPrefix("metar") {
      pathPrefix(Segment) { stationId =>
        parameter('hrsBefore.as[Double].?) {
          case Some(hrsBefore) => getMetars(stationId, hrsBefore)
          case None => getCurrentMetar(stationId)
        }
      }
    }

  def getCurrentMetar(stationId: String): Route = {
    onSuccess(metarService.getCurrentMetar(stationId)) { response =>
      completeResponse(response)
    }
  }

  def getMetars(stationId: String, hrsBefore: Double): Route = {
    onSuccess(metarService.getMetars(stationId, hrsBefore)) { response =>
      completeResponse(response)
    }
  }

  private def completeResponse(response: Seq[METAR]): Route = {
    logger.debug(s"Response collection size: ${response.size}")
    if (response.isEmpty) {
      extractRequest { request =>
        complete(NotFound, s"Station with ICAO id ${request.uri.toString().split("/").last} Not Found")
      }
    }
    else complete(seqToHttpResponse(response))
  }

}
