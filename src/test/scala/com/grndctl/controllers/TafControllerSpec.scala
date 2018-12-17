package com.grndctl.controllers

import akka.http.scaladsl.server.Route
import com.grndctl.BaseSpec
import com.grndctl.model.taf.{TAF, TimeType}
import com.grndctl.services.TafSvc

import scala.concurrent.Future
import scala.collection.JavaConverters._

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class TafControllerSpec extends BaseSpec {

  val tafSvc: TafSvc = mock[TafSvc]
  val tafRoute: Route = new TafController(tafSvc).route
  val hrsBefore: Int = 5
  val stationId: String = "KDEN"
  val invalidId: String = "XXXX"

  // This is ugly but necessary to convert from the LinkedHashMap representation of a TAF.
  val validTafSeq: Seq[TAF] =
    OM.readValue(
      validTafStr,
      OM.getTypeFactory.constructCollectionType(classOf[java.util.List[TAF]], classOf[TAF])
    ).asInstanceOf[java.util.List[TAF]].asScala

  "The TAF controller" should {

    s"return 200/$stationId TAFs from GET:/taf/$stationId?hrsBefore=$hrsBefore&timeType=valid" in {
      (tafSvc.getTafs _)
        .expects(stationId, hrsBefore, TimeType.VALID)
        .returning(Future(validTafSeq))
      Get(s"/taf/$stationId?hrsBefore=$hrsBefore&timeType=valid") ~> Route.seal(tafRoute) ~> check {
        val expected: TAF = validTafSeq.head
        val actual: TAF = entityToCollectionType(classOf[TAF]).head
        expected shouldEqual actual
      }
    }

    s"return 404/Not Found from GET:/taf/$invalidId?hrsBefore=$hrsBefore&timeType=valid" in {
      (tafSvc.getTafs _)
        .expects(invalidId, hrsBefore, TimeType.VALID)
        .returning(Future.successful(Seq.empty))
      Get(s"/taf/$invalidId?hrsBefore=$hrsBefore&timeType=valid") ~> Route.seal(tafRoute) ~> check {
        response.status.intValue shouldEqual 404
      }
    }

  }
}
