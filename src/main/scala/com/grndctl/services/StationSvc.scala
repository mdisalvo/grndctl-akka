package com.grndctl.services

import java.io.InputStreamReader
import java.net.URL

import com.google.common.io.CharStreams
import com.grndctl.misc.InputStreamUnmarshaller
import com.grndctl.model.station
import com.grndctl.model.station.{FaaStation, Station}
import com.typesafe.scalalogging.LazyLogging

import scala.collection.JavaConverters._
import scala.concurrent.{ExecutionContext, Future}

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class StationSvc(implicit ec: ExecutionContext)
    extends InputStreamUnmarshaller[station.Response](classOf[station.Response])
    with LazyLogging {

  private val AddsRqstUrl = "https://aviationweather.gov/adds/dataserver_current/httpparam?datasource=stations" +
    "&requesttype=retrieve&format=xml"

  private val FaaRqstUrl = "https://soa.smext.faa.gov/asws/api/airport/status/"

  private val StationString = "&stationString="

  def getStationInfo(icaoCode: String): Future[Seq[Station]] = {
    val url: URL = new URL(AddsRqstUrl + StationString + icaoCode.toUpperCase)
    unmarshall(url)
      .map(_.getData.getStation.asScala)
      .recover {
        case e: Exception =>
          logger.error("Exception when retrieving ADDS status.", e)
          Seq.empty
      }
  }

  def getFaaStationStatus(iataCode: String): Future[Option[FaaStation]] = {
    val url: URL = new URL(FaaRqstUrl + iataCode)
    Future {
      autoClose(new InputStreamReader(url.openStream())) { isr =>
        Option(
          OM.readValue(
            CharStreams.toString(isr).getBytes(),
            classOf[FaaStation]
          )
        )
      }
    }.recover {
      case e: Exception =>
        logger.error("Exception when retrieving FAA status.", e)
        None
    }
  }

}
