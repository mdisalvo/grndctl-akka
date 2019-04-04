package com.grndctl.controllers

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Route
import com.grndctl.BaseSpec
import com.grndctl.model.metar.METAR
import com.grndctl.services.MetarSvc

import scala.concurrent.Future


/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class MetarControllerSpec extends BaseSpec {

  val metarSvc: MetarSvc = mock[MetarSvc]
  val metarRoute: Route = new MetarController(metarSvc).route
  val hrsBefore: Double = 3.0
  val stationId: String = "KDEN"

  val validMetar: METAR = OM.readValue(validMetarStr, classOf[METAR])
  val validMetarSeq: Seq[METAR] = Seq(validMetar, validMetar)

  "The METAR controller" should {

    s"return 200/Current $stationId METAR from GET:/metar/$stationId" in {
      (metarSvc.getCurrentMetar _)
        .expects(stationId)
        .returning(Future(validMetarSeq))
      Get(s"/metar/$stationId") ~> Route.seal(metarRoute) ~> check {
        response.status shouldBe OK
        val expected: METAR = validMetarSeq.head
        val actual: METAR = entityAsSeqType(classOf[METAR]).head
        expected shouldEqual actual
      }
    }

    s"return 200/$stationId METARs from GET:/metar/$stationId?hrsBefore=$hrsBefore" in {
      (metarSvc.getMetars _)
        .expects(stationId, hrsBefore)
        .returning(Future(validMetarSeq))
      Get(s"/metar/$stationId?hrsBefore=$hrsBefore") ~> Route.seal(metarRoute) ~> check {
        response.status shouldBe OK
        val expected: Seq[METAR] = validMetarSeq
        val actual: Seq[METAR] = entityAsSeqType(classOf[METAR])
        expected shouldEqual actual
      }
    }

    s"return 404/Not Found from GET:/metar/XXXX?hrsBefore=$hrsBefore" in {
      (metarSvc.getMetars _)
        .expects("XXXX", hrsBefore)
        .returning(Future(Seq.empty))
      Get(s"/metar/XXXX?hrsBefore=$hrsBefore") ~> Route.seal(metarRoute) ~> check {
        val expected: String = "Station with ICAO id XXXX?hrsBefore=3.0 Not Found"
        val actual: String = responseAs[String]
        response.status shouldBe NotFound
        expected shouldEqual actual
      }
    }

  }
}
