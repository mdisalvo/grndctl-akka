package com.grndctl.services

import com.grndctl.misc.AutoClose
import com.grndctl.model.misc.Airline
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.{ExecutionContext, Future}
import scala.io.Source

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class AirlineSvc(airlineList:    Seq[Airline],
                 icaoAirlineMap: Map[String, Airline],
                 iataAirlineMap: Map[String, Airline])
                (implicit ec: ExecutionContext) extends LazyLogging {

  def getAirlineByIcao(station: String): Future[Option[Airline]] =
    Future.successful(icaoAirlineMap.get(station).orElse(None))

  def getAirlineByIata(station: String): Future[Option[Airline]] =
    Future.successful(iataAirlineMap.get(station).orElse(None))

  def getActiveAirlines: Future[Seq[Airline]] =
    Future.successful(airlineList.filter(a => a.getActive.equals("Y")))

  def getAllAirlines: Future[Seq[Airline]] = Future.successful(airlineList)

}

object AirlineSvc extends AutoClose {

  def apply(implicit ec: ExecutionContext): AirlineSvc = {
    var airlineList:    Seq[Airline] = Seq.empty
    var icaoAirlineMap: Map[String, Airline] = Map.empty
    var iataAirlineMap: Map[String, Airline] = Map.empty

    autoClose(Source.fromResource("airlines.dat.txt")) { bs =>
      for(line <- bs.getLines()) {
        val a: Airline = new Airline
        line.split(",").map(_.trim).zipWithIndex.foreach { elem =>
          elem._2 match {
            case 0 => a.setId(elem._1.replaceAll("\"", ""))
            case 1 => a.setName(elem._1.replaceAll("\"", ""))
            case 2 => a.setAlias(elem._1.replaceAll("\"", ""))
            case 3 => a.setIata(elem._1.replaceAll("\"", ""))
            case 4 => a.setIcao(elem._1.replaceAll("\"", ""))
            case 5 => a.setCallsign(elem._1.replaceAll("\"", ""))
            case 6 => a.setCountry(elem._1.replaceAll("\"", ""))
            case 7 => a.setActive(elem._1.replaceAll("\"", ""))
            case 8 => println(elem._1.replaceAll("\"", ""))
          }
        }
        icaoAirlineMap += (a.getIcao -> a)
        iataAirlineMap += (a.getIata -> a)
        airlineList = airlineList :+ a
      }
      new AirlineSvc(airlineList, icaoAirlineMap, iataAirlineMap)
    }

  }

}
