package com.grndctl.controllers

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.StatusCodes._
import com.grndctl.misc.HttpResponseSupport
import com.grndctl.model.flightplan.Notam._
import com.grndctl.services.NotamSvc
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class NotamController(notamService: NotamSvc)(implicit ec: ExecutionContext) extends HttpResponseSupport with LazyLogging {

  val route: Route = {
    pathPrefix("notam") {
      // TODO make this 'codes'
      parameters('code, 'reportType.?, 'formatType.?) { (code, reportType, formatType) =>
        getNotamsForCodes(code.toUpperCase, reportType, formatType)
      }
    }
  }

  def getNotamsForCodes(code: String, reportType: Option[String], formatType: Option[String]): Route = {
    onSuccess(
      notamService
        .getNotamsForCodes(
          Seq(code),
          if (reportType.isEmpty) ReportType.RAW else ReportType.fromString(reportType.get),
          if (formatType.isEmpty) FormatType.DOMESTIC else FormatType.fromString(formatType.get)
        )
    ) { response => 
      if (response.isEmpty) {
        complete(NotFound, s"NOTAMs for ICAO code $code not found.")
      } else {
        complete(seqToHttpResponse(response)) 
      }
    }
  }

}
