package com.grndctl.controllers

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{RejectionHandler, Route}
import com.grndctl.services.MetarSvc
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class MetarController(service: MetarSvc)
                     (implicit ec: ExecutionContext)
  extends SprayJsonSupport with LazyLogging with HttpResponseSupport {

  val route: Route =
    handleRejections(RejectionHandler.default) {
      pathPrefix("metar") {
        pathPrefix(Segment) { stationId =>
          onSuccess(service.getCurrentMetar(stationId)) { responses =>
            complete(seqToHttpResponse(responses))
          }
        }
      }
    }

}
