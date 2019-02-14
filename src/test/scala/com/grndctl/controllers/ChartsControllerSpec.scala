package com.grndctl.controllers

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Route
import com.grndctl.BaseSpec
import com.grndctl.services.ChartsSvc

import scala.concurrent.Future

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class ChartsControllerSpec extends BaseSpec {

  val chartsSvc: ChartsSvc = mock[ChartsSvc]
  val chartsRoute: Route = new ChartsController(chartsSvc).route
  val validIcao: String = "KIAD"

  "The Charts Controller" should {

    s"return 200/String from GET:/charts/$validIcao" in {
      (chartsSvc.getStationCharts _)
        .expects(validIcao)
        .returning(Future(validChartStr))
      Get(s"/charts/$validIcao") ~> chartsRoute ~> check {
        response.status shouldBe OK
        validChartStr shouldEqual responseAs[String]
      }
    }

    s"return 404/ NotFound from GET:/charts/XXXX" in {
      (chartsSvc.getStationCharts _)
        .expects("XXXX")
        .returning(Future("Not Found"))
      Get("/charts/XXXX") ~> chartsRoute ~> check {
        response.status shouldBe NotFound
      }
    }

  }


}
