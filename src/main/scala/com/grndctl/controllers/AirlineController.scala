package com.grndctl.controllers

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.grndctl.misc.HttpResponseSupport
import com.grndctl.services.AirlineSvc
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class AirlineController(airlineService: AirlineSvc)
                       (implicit ec: ExecutionContext)
  extends HttpResponseSupport with LazyLogging {

  val route: Route =
    pathPrefix("airline") {
      pathEndOrSingleSlash {
        getAllAirlines
      } ~
        pathPrefix("icao") {
          pathPrefix(Segment) { icao =>
            getAirlineByIcao(icao.toUpperCase)
          }
        } ~
        pathPrefix("iata") {
          pathPrefix(Segment) { iata =>
            getAirlineByIata(iata.toUpperCase)
          }
        } ~
        pathPrefix("active") {
          getActiveAirlines
        }
    }

  def getAllAirlines: Route = onSuccess(airlineService.getAllAirlines)(response =>
    complete(seqToHttpResponse(response))
  )

  def getAirlineByIcao(icao: String): Route = onSuccess(airlineService.getAirlineByIcao(icao))(response =>
    if (response.isDefined) {
      complete(anyToHttpResponse(response.get))
    } else {
      complete(NotFound)
    }
  )

  def getAirlineByIata(iata: String): Route = onSuccess(airlineService.getAirlineByIata(iata))(response =>
    if (response.isDefined) {
      complete(anyToHttpResponse(response.get))
    } else {
      complete(NotFound)
    }
  )

  def getActiveAirlines: Route = onSuccess(airlineService.getActiveAirlines)(response =>
    complete(seqToHttpResponse(response))
  )

}
