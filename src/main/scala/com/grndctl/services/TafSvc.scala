package com.grndctl.services

import java.net.URL

import com.grndctl.misc.InputStreamUnmarshaller
import com.grndctl.model.taf
import com.grndctl.model.taf.{TAF, TimeType}
import com.typesafe.scalalogging.LazyLogging

import scala.collection.JavaConverters._
import scala.concurrent.{ExecutionContext, Future}

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class TafSvc(implicit ec: ExecutionContext)
  extends InputStreamUnmarshaller[taf.Response](classOf[taf.Response]) with LazyLogging {

  private val RequestUrl: String = "https://aviationweather.gov/adds/dataserver_current/httpparam?dataSource=tafs" +
    "&requestType=retrieve&format=xml"

  private val MostRecentConstraint: String = "&mostRecentForEachStation=constraint"

  private val StationString: String = "&stationString="

  private val HrsBefore: String = "&hoursBeforeNow="

  private val TimeType: String = "&timeType="

  /**
    *
    *
    * @param stationId
    * @param hrsBefore
    * @param timeType
    * @return
    */
  def getTafs(stationId: String, hrsBefore: Double, timeType: TimeType): Future[Seq[TAF]] = {
    unmarshall(new URL(RequestUrl + StationString + stationId + HrsBefore + hrsBefore + TimeType + timeType.valueOf()))
      .map(_.getData.getTAF.asScala)
      .recover {
        case e: Exception =>
          logger.error(ErrorMessage, e)
          Seq.empty
      }
  }

}
