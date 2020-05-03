package com.grndctl.services

import java.net.URL

import com.grndctl.misc.InputStreamUnmarshaller
import com.grndctl.model.metar
import com.grndctl.model.metar.METAR
import com.typesafe.scalalogging.LazyLogging

import scala.collection.JavaConverters._
import scala.concurrent.{ExecutionContext, Future}

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class MetarSvc(implicit ec: ExecutionContext)
  extends InputStreamUnmarshaller[metar.Response](classOf[metar.Response]) with LazyLogging {

  private val RequestUrl: String = "https://aviationweather.gov/adds/dataserver_current/httpparam?dataSource=metars" +
    "&requestType=retrieve&format=xml"

  private val HrsBefore: String = "&hoursBeforeNow="

  private val StationString: String = "&stationString="

  private val MostRecentConstraint: String = "&mostRecentForEachStation=constraint"

  private val OneHr: Float = 1

  def getCurrentMetar(station: String): Future[Seq[METAR]] = {
    unmarshall(new URL(RequestUrl + MostRecentConstraint + StationString + station + HrsBefore + OneHr))
      .map(_.getData.getMETAR.asScala)
      .recover {
        case e: Exception =>
          logger.error(ErrorMessage, e)
          Seq.empty
      }
  }

  def getMetars(station: String, hrsBefore: Double): Future[Seq[METAR]] = {
    unmarshall(new URL(RequestUrl + HrsBefore + hrsBefore + StationString + station))
      .map(_.getData.getMETAR.asScala)
      .recover {
        case e: Exception =>
          logger.error(ErrorMessage, e)
          Seq.empty
      }
  }

}
