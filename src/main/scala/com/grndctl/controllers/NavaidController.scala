package com.grndctl.controllers

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.grndctl.misc.HttpResponseSupport
import com.grndctl.services.NavaidSvc
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class NavaidController(navaidService: NavaidSvc)(implicit ec: ExecutionContext)
  extends HttpResponseSupport with LazyLogging {

  val route: Route =
    pathPrefix("navaid") {
      pathEndOrSingleSlash {
        getAllNavaidsByIdent
      } ~
      pathPrefix("ident") {
        pathPrefix(Segment) { ident =>
          getNavaidsByIdent(ident)
        }
      } ~
      pathPrefix("station") {
        pathPrefix(Segment) { station =>
          getNavaidsByStation(station)
        }
      }
    }

  // TODO Make this pageable.
  def getAllNavaidsByIdent: Route = onSuccess(navaidService.getAllNavaidsByIdent)(response =>
    complete(seqMultiMapToHttpResponse(response))
  )

  def getNavaidsByIdent(ident: String): Route = onSuccess(navaidService.getNavaidByIdent(ident.toUpperCase)) {
    response =>
      if (response.isDefined) {
        complete(seqToHttpResponse(response.get))
      } else {
        complete(NotFound, s"Navaids for ident $ident not found.")
      }
  }

  def getNavaidsByStation(station: String): Route = onSuccess(navaidService.getNavaidByStation(station.toUpperCase)) {
    response =>
      if (response.isDefined) {
        complete(seqToHttpResponse(response.get))
      } else {
        complete(NotFound, s"Navaids for station $station not found.")
      }
  }

}
