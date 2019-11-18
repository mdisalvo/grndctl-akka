package com.grndctl.controllers

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Route
import com.grndctl.BaseSpec
import com.grndctl.model.station.Station
import com.grndctl.services.StationSvc

import scala.collection.JavaConverters._
import scala.concurrent.Future

class StationControllerSpec extends BaseSpec {

  val stationSvc: StationSvc = mock[StationSvc]
  val stationRoute: Route = new StationController(stationSvc).route
  val station: String = "KDEN"
  val invalidStation: String = "XXXX"

  val validStationSeq: Seq[Station] =
    strToSeqOfType(validStationSeqStr, classOf[Station])

  s"return 200/[Station] from GET:/station/adds/$station" in {
    (stationSvc.getStationInfo _)
      .expects(station)
      .returning(Future(validStationSeq))
    Get(s"/station/adds/$station") ~> stationRoute ~> check {
      response.status shouldBe OK
      val expected: Seq[Station] = validStationSeq
      val actual: Seq[Station] = entityAsSeqType(classOf[Station])
      expected shouldEqual actual
    }
  }

  s"return 404/Not Found from GET:/station/adds/$invalidStation" in {
    (stationSvc.getStationInfo _)
      .expects(invalidStation)
      .returning(Future(Seq.empty))
    Get(s"/station/adds/$invalidStation") ~> stationRoute ~> check {
      response.status shouldBe NotFound
    }
  }

}
