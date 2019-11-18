package com.grndctl.services

import java.io.{FileNotFoundException, InputStreamReader}
import java.net.{HttpURLConnection, URL}

import com.google.common.io.CharStreams
import com.grndctl.misc.AutoClose
import com.typesafe.scalalogging.LazyLogging
import org.json.JSONObject

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class ChartsSvc(implicit ec: ExecutionContext)
    extends AutoClose
    with LazyLogging {

  private val AirchartsUrl = "https://api.aircharts.org/v2/Airport/"

  private val ThanksValue =
    "All information retrieved from AirCharts at http://www.aircharts.org"

  private val ThanksKey = "Thanks"

  def getStationCharts(station: String): Future[String] = {
    Future {
      val urlConn = new URL(s"$AirchartsUrl$station")
        .openConnection()
        .asInstanceOf[HttpURLConnection]
      urlConn.setRequestProperty("User-Agent", "akka-http/10.1.3")
      autoClose(urlConn.getInputStream) { s =>
        autoClose(new InputStreamReader(s)) { isr =>
          new JSONObject(CharStreams.toString(isr))
            .put(ThanksKey, ThanksValue)
            .toString
        }
      }
    }.recover {
      case fnf: FileNotFoundException => "Not Found"
    }
  }

}
