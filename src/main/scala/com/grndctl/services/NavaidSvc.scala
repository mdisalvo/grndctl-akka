package com.grndctl.services

import com.google.common.base.Strings
import com.grndctl.misc.AutoClose
import com.grndctl.model.flightplan.Navaid
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.{ExecutionContext, Future}
import scala.io.Source

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class NavaidSvc(identNavaidMap: Map[String, Seq[Navaid]],
                stationNavaidMap: Map[String, Seq[Navaid]])
               (implicit ec: ExecutionContext) extends LazyLogging {

  def getAllNavaidsByIdent: Future[Map[String, Seq[Navaid]]] =
    Future.successful(identNavaidMap)

  def getNavaidByIdent(ident: String): Future[Option[Seq[Navaid]]] =
    Future.successful(identNavaidMap.get(ident).orElse(None))

  def getNavaidByStation(station: String): Future[Option[Seq[Navaid]]] =
    Future.successful(stationNavaidMap.get(station).orElse(None))

}

object NavaidSvc extends AutoClose {

  def apply(implicit ec: ExecutionContext): NavaidSvc = {
    val identNavaidMap = new ListMultiMap[String, Navaid]
    val stationNavaidMap = new ListMultiMap[String, Navaid]

    autoClose(Source.fromResource("navaids.csv")) { bs =>
      for (line <- bs.getLines()) {
        val n: Navaid = new Navaid
        line.split(",").map(_.trim).zipWithIndex.foreach { elem =>
          elem._2 match {
            case 0 => n.setId(elem._1.replaceAll("\"", ""))
            case 1 => n.setFilename(elem._1.replaceAll("\"", ""))
            case 2 => n.setIdent(elem._1.replaceAll("\"", ""))
            case 3 => n.setName(elem._1.replaceAll("\"", ""))
            case 4 => n.setType(elem._1.replaceAll("\"", ""))
            case 5 => if (!Strings.isNullOrEmpty(elem._1)) {
              n.setFrequencyKhz(elem._1.replaceAll("\"", "").toInt)
            }
            case 6 => if (!Strings.isNullOrEmpty(elem._1)) {
              n.setLatitudeDeg(elem._1.replaceAll("\"", "").toDouble)
            }
            case 7 => if (!Strings.isNullOrEmpty(elem._1)) {
              n.setLongitudeDeg(elem._1.replaceAll("\'", "'").toDouble)
            }
            case 8 => if (!Strings.isNullOrEmpty(elem._1)) {
              n.setElevationFt(elem._1.replaceAll("\"", "").toInt)
            }
            case 9 => n.setIsoCountry(elem._1.replaceAll("\'", ""))
            case 10 => if (!Strings.isNullOrEmpty(elem._1)) {
              n.setDmeFrequencyKhz(elem._1.replaceAll("\"", "").toInt)
            }
            case 11 => n.setDmeChannel(elem._1.replaceAll("\"", ""))
            case 12 => if (!Strings.isNullOrEmpty(elem._1)) {
              n.setDmeLatitudeDeg(elem._1.replaceAll("\"", "").toDouble)
            }
            case 13 => if (!Strings.isNullOrEmpty(elem._1)) {
              n.setDmeLongitudeDeg(elem._1.replaceAll("\"", "").toDouble)
            }
            case 14 => if (!Strings.isNullOrEmpty(elem._1)) {
              n.setDmeElevationFt(elem._1.replaceAll("\"", "").toInt)
            }
            case 15 => if (!Strings.isNullOrEmpty(elem._1)) {
              n.setSlavedVariationDeg(elem._1.replaceAll("\"", "").toDouble)
            }
            case 16 => if (!Strings.isNullOrEmpty(elem._1)) {
              n.setMagneticVariationDeg(elem._1.replaceAll("\"", "").toDouble)
            }
            case 17 => n.setUsageType(elem._1.replaceAll("\'", ""))
            case 18 => n.setPower(elem._1.replaceAll("\"", ""))
            case 19 => n.setAssociatedAirport(elem._1.replaceAll("\"", ""))
          }
        }
        identNavaidMap.put(n.getIdent, n)
        stationNavaidMap.put(n.getAssociatedAirport, n)
      }
      new NavaidSvc(identNavaidMap.toMap, stationNavaidMap.toMap)
    }

  }
}
