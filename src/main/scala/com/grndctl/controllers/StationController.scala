package com.grndctl.controllers

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.grndctl.misc.HttpResponseSupport
import com.grndctl.services.StationSvc
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class StationController(service: StationSvc)
                       (implicit ec: ExecutionContext)
  extends HttpResponseSupport with LazyLogging {

  val route: Route =
    pathPrefix("station" / "adds") {
      path(Segment) { stationId =>
        onSuccess(service.getStationInfo(stationId)) { station =>
          if (station.isEmpty) {
            complete(NotFound)
          } else {
            complete(seqToHttpResponse(station))
          }
        }
      }
    } /**~
    pathPrefix("station" / "faa") {
      path(Segment) { stationId =>
        onSuccess(service.getFaaStationStatus(stationId)) {
          case Some(station) => complete(anyToHttpResponse(station))
          case None => complete(NotFound)
        }
      }
    }**/

}
