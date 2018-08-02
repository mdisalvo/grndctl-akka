package com.grndctl.services

import com.grndctl.model.flightplan.Notam
import com.typesafe.scalalogging.LazyLogging
import org.jsoup.Jsoup

import scala.collection.JavaConverters._
import scala.concurrent.{ExecutionContext, Future}

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class NotamSvc(implicit ec: ExecutionContext) extends LazyLogging {

  private val Faa_Notam_Search_Url = "https://pilotweb.nas.faa.gov/PilotWeb/notamRetrievalByICAOAction.do?" + "method=displayByICAOs&actionType=notamRetrievalByICAOs"
  private val Format_Type = "&formatType="
  private val Report_Type = "&reportType="
  private val Icao_Code = "&retrieveLocId="
  private val User_Agent = "akka-http/10.1.3"
  private val Notam_Right = "notamRight"

  def getNotamsForCodes(codes: Seq[String], reportType: Notam.ReportType, formatType: Notam.FormatType): Future[Seq[String]] = {
    val icaoString = codes.mkString(",")
    Future(
      Jsoup
        .connect(
          Faa_Notam_Search_Url +
            Icao_Code + icaoString +
            Report_Type + reportType +
            Format_Type + formatType
        )
        .userAgent(User_Agent)
        .timeout(5000)
        .get
        .getElementsByAttributeValue("id", Notam_Right).asScala.map(_.text)
    )
  }
}
