package com.grndctl.controllers

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.util.ByteString
import com.grndctl.misc.HttpResponseSupport
import com.grndctl.services.ChartsSvc
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class ChartsController(chartsService: ChartsSvc)
                      (implicit ec: ExecutionContext)
  extends HttpResponseSupport with LazyLogging {

  val route: Route =
    pathPrefix("charts") {
      pathPrefix(Segment) { station =>
        getStationCharts(station)
      }
    }

  def getStationCharts(station: String): Route = {
    onSuccess(chartsService.getStationCharts(station)) { response =>
      if (response.contains("Not Found")) {
        complete(NotFound, s"Charts for station with ICAO id $station not found.")
      } else {
        complete(
          ToResponseMarshallable.apply(
              HttpResponse(
              status = 200,
              entity = HttpEntity(ContentTypes.`application/json`, ByteString(response))
            )
          )
        )
      }
    }
  }

}
