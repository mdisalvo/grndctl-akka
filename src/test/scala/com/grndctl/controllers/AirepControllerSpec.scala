package com.grndctl.controllers

import akka.http.scaladsl.server.Route
import com.grndctl.BaseSpec
import com.grndctl.model.aircraftrep.{AircraftReport, ReportType}
import com.grndctl.services.AirepSvc

import scala.collection.JavaConverters._
import scala.concurrent.Future

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class AirepControllerSpec extends BaseSpec {

  val airepSvc: AirepSvc = mock[AirepSvc]
  val airepRoute: Route = new AirepController(airepSvc).route
  val hrsBefore: Double = 2.0
  val reportType: ReportType = ReportType.AIREP

  // More ugs-ma-gugs.
  val validAirepSeq: Seq[AircraftReport] =
    OM.readValue(
      validAirepStr,
      OM.getTypeFactory.constructCollectionType(classOf[java.util.List[AircraftReport]], classOf[AircraftReport])
    ).asInstanceOf[java.util.List[AircraftReport]].asScala

  "The AIREP Controller" should {

    s"return 200/AIREPs from GET:/airep?hrsBefore=2.0&reportType=AIREP" in {
      (airepSvc.getAircraftReports _)
        .expects(hrsBefore, reportType)
        .returning(Future(validAirepSeq))
      Get(s"/airep?hrsBefore=$hrsBefore&reportType=AIREP") ~> Route.seal(airepRoute) ~> check {
        val expected: AircraftReport = validAirepSeq.head
        val actual: AircraftReport = entityToCollectionType(classOf[AircraftReport]).head
        expected shouldEqual actual
      }
    }

    s"return 400/Bad Request from GET:/airep?hrsBefore=two&reportType=FAKE" in {
      Get(s"/airep?hrsBefore=two&reportType=FAKE") ~> Route.seal(airepRoute) ~> check {
        response.status.intValue shouldEqual 400
      }
    }

  }


}
