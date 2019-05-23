package com.grndctl.controllers

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Route
import com.grndctl.BaseSpec
import com.grndctl.model.flightplan.Notam.{FormatType, ReportType}
import com.grndctl.services.NotamSvc
import org.scalamock.handlers.CallHandler3

import scala.collection.JavaConverters._
import scala.concurrent.Future

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class NotamControllerSpec extends BaseSpec {

  val notamSvc: NotamSvc = mock[NotamSvc]
  val notamRoute: Route = new NotamController(notamSvc).route
  val notamStation: String = "KDEN"
  val validReportType: String = "RAW"
  val validFormatType: String = "DOMESTIC"
  val invalidReportType: String = "WAR"
  val invalidFormatType: String = "INTL"

  val validNotamSeq: Seq[String] =
    OM.readValue(
      validNotamSeqStr,
      OM.getTypeFactory.constructCollectionType(classOf[java.util.List[String]], classOf[String])
    ).asInstanceOf[java.util.List[String]].asScala

  s"return 200/[String] from GET:/notam?code=$notamStation" in {
    validNotamRouteRequest()
    Get(s"/notam?code=$notamStation") ~> notamRoute ~> check {
      response.status shouldBe OK
      val expected: Seq[String] = validNotamSeq
      val actual: Seq[String] = entityAsSeqType(classOf[String])
      expected shouldEqual actual
    }
  }

  s"return 200/[String] from GET:/notam?code=$notamStation&reportType=$validReportType&formatType=$validFormatType" in {
    validNotamRouteRequest()
    Get(s"/notam?code=$notamStation&reportType=$validReportType&formatType=$validFormatType") ~> notamRoute ~> check {
      response.status shouldBe OK
      val expected: Seq[String] = validNotamSeq
      val actual: Seq[String] = entityAsSeqType(classOf[String])
      expected shouldEqual actual
    }
  }

  s"return 404/ Not Found from GET:/notam?code=FAKE" in {
    (notamSvc.getNotamsForCodes _)
      .expects(Seq("FAKE"), ReportType.fromString(validReportType), FormatType.fromString(validFormatType))
      .returning(Future(Seq.empty))
    Get(s"/notam?code=FAKE") ~> notamRoute ~> check {
      response.status shouldBe NotFound
      entityAs[String] shouldEqual "NOTAMs for ICAO code FAKE not found."
    }
  }

  s"return 400/ Bad Request from GET:/notam?code=$notamStation&reportType=$invalidReportType" in pending
//    Get(s"/notam?code=$notamStation&reportType=$invalidReportType") ~> notamRoute ~> check {
//      response.status shouldBe BadRequest

  def validNotamRouteRequest(): CallHandler3[Seq[String], ReportType, FormatType, Future[Seq[String]]] = {
    (notamSvc.getNotamsForCodes _)
      .expects(Seq(notamStation), ReportType.fromString(validReportType), FormatType.fromString(validFormatType))
      .returning(Future(validNotamSeq))
  }

}
