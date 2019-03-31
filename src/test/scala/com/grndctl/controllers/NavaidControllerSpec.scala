package com.grndctl.controllers

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Route
import com.grndctl.BaseSpec
import com.grndctl.model.flightplan.Navaid
import com.grndctl.services.NavaidSvc

import scala.collection.JavaConverters._
import scala.concurrent.Future

/**
 * @author Michael Di Salvo
 * michael.vincent.disalvo@gmail.com
 */
class NavaidControllerSpec extends BaseSpec {

  val navaidSvc: NavaidSvc = mock[NavaidSvc]
  val navaidRoute: Route = new NavaidController(navaidSvc).route
  val navaidIdent: String = "AML"
  val navaidStation: String = "KIAD"

  val validNavaidSeqMultiMap: Map[String, Seq[Navaid]] =
    OM.readValue (
      validNavaidSeqMultiMapStr,
      OM.getTypeFactory.constructMapType(
        classOf[java.util.Map[String, java.util.List[Navaid]]], classOf[String], classOf[java.util.List[Navaid]]
      )
    ).asInstanceOf[java.util.Map[String, java.util.List[Navaid]]].asScala.map(e => (e._1, e._2.asScala)).toMap

  val validNavaidSeq: Seq[Navaid] =
    OM.readValue(
      validNavaidSeqStr,
      OM.getTypeFactory.constructCollectionType(classOf[java.util.List[Navaid]], classOf[Navaid])
    ).asInstanceOf[java.util.List[Navaid]].asScala

  "The Navaid Controller" should {

    s"return 200/[String, [Navaid]] from GET:/navaid" in {
      (navaidSvc.getAllNavaidsByIdent _)
        .expects()
        .returning(Future(validNavaidSeqMultiMap))
      Get("/navaid") ~> navaidRoute ~> check {
        response.status shouldBe OK
        val expected: Map[String, Seq[Navaid]] = validNavaidSeqMultiMap
        val actual: Map[String, Seq[Navaid]] = entityAsMapType(classOf[Navaid])
        expected shouldEqual actual
      }
    }

    s"return 200/[Navaid] from GET:/navaid/ident/$navaidIdent" in {
      (navaidSvc.getNavaidByIdent _)
        .expects(navaidIdent)
        .returning(Future(Some(validNavaidSeq)))
      Get(s"/navaid/ident/$navaidIdent") ~> navaidRoute ~> check {
        response.status shouldBe OK
        val expected: Seq[Navaid] = validNavaidSeq
        val actual: Seq[Navaid] = entityAsSeqType(classOf[Navaid])
        expected shouldEqual actual
      }
    }

    s"return 404/ Not Found from GET:/navaid/ident/XXX" in {
      (navaidSvc.getNavaidByIdent _)
        .expects("XXX")
        .returning(Future(None))
      Get("/navaid/ident/XXX") ~> navaidRoute ~> check {
        response.status shouldBe NotFound
      }
    }

    s"return 200/[Navaid] from GET:/navaid/station/$navaidStation" in {
      (navaidSvc.getNavaidByStation _)
        .expects(navaidStation)
        .returning(Future(Some(validNavaidSeq)))
      Get(s"/navaid/station/$navaidStation") ~> navaidRoute ~> check {
        response.status shouldBe OK
        val expected: Seq[Navaid] = validNavaidSeq
        val actual: Seq[Navaid] = entityAsSeqType(classOf[Navaid])
        expected shouldEqual actual
      }
    }

    s"return 404/ Not Found from GET:/navaid/station/XXXX" in {
      (navaidSvc.getNavaidByStation _)
        .expects("XXXX")
        .returning(Future(None))
      Get("/navaid/station/XXXX") ~> navaidRoute ~> check {
        response.status shouldBe NotFound
      }
    }

  }

}
