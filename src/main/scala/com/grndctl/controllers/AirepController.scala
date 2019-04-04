package com.grndctl.controllers

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.grndctl.misc.HttpResponseSupport
import com.grndctl.model.aircraftrep.ReportType
import com.grndctl.services.AirepSvc
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class AirepController(airepService: AirepSvc)
                     (implicit ec: ExecutionContext)
  extends HttpResponseSupport with LazyLogging {

  val route: Route =
    pathPrefix("airep") {
      parameters('hrsBefore.as[Double], 'reportType ? ReportType.AIREP.toString) { (hrsBefore, reportType) =>
        validate(ReportType.fromString(reportType).isDefined, s"Invalid ReportType $reportType.") {
          getAireps(hrsBefore, ReportType.fromString(reportType).get)
        }
      }
    }

  def getAireps(hrsBefore: Double, reportType: ReportType): Route = {
    onSuccess(airepService.getAircraftReports(hrsBefore, reportType)) { response =>
      complete(seqToHttpResponse(response))
    }
  }

}
