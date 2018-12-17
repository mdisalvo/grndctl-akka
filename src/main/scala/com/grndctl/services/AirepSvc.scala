package com.grndctl.services

import java.net.URL

import com.grndctl.misc.InputStreamUnmarshaller
import com.grndctl.model._
import com.grndctl.model.aircraftrep.{AircraftReport, ReportType}
import com.typesafe.scalalogging.LazyLogging

import scala.collection.JavaConverters._
import scala.concurrent.{ExecutionContext, Future}

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class AirepSvc(implicit ec: ExecutionContext)
  extends InputStreamUnmarshaller[aircraftrep.Response](classOf[aircraftrep.Response]) with LazyLogging {

  private val RequestUrl: String = "https://aviationweather.gov/adds/dataserver_current/httpparam?" +
    "datasource=aircraftreports&requesttype=retrieve&format=xml"

  private val HrsBefore: String = "&hoursBeforeNow="

  /**
    *
    *
    * @param hrsBefore
    * @param reportType
    * @return
    */
  def getAircraftReports(hrsBefore: Double, reportType: ReportType): Future[Seq[AircraftReport]] = {
    unmarshall(new URL(RequestUrl + HrsBefore + hrsBefore))
      .map(_.getData.getAircraftReport.asScala)
      .recover {
        case e: Exception =>
          logger.error(ErrorMessage)
          Seq.empty
      }
  }

}
