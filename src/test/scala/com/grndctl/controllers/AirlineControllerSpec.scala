package com.grndctl.controllers

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Route
import com.grndctl.BaseSpec
import com.grndctl.model.misc.Airline
import com.grndctl.services.AirlineSvc

import scala.collection.JavaConverters._
import scala.concurrent.Future

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class AirlineControllerSpec extends BaseSpec {

  val airlineSvc: AirlineSvc = mock[AirlineSvc]
  val airlineRoute: Route = new AirlineController(airlineSvc).route
  val airlineIcao: String = "UAL"
  val airlineIata: String = "UA"

  val validAirlineSeq: Seq[Airline] =
    OM.readValue(
      validAirlineStr,
      OM.getTypeFactory.constructCollectionType(classOf[java.util.List[Airline]], classOf[Airline])
    ).asInstanceOf[java.util.List[Airline]].asScala

  "The Airline Controller" should {

    s"return 200/[Airline] from GET:/airline" in {
      (airlineSvc.getAllAirlines _)
        .expects()
        .returning(Future(validAirlineSeq))
      Get("/airline") ~> airlineRoute ~> check {
        response.status shouldBe OK
        val expected: Seq[Airline] = validAirlineSeq
        val actual: Seq[Airline] = entityAsSeqType(classOf[Airline])
        expected shouldEqual actual
      }
    }

    s"return 200/Airline from GET:/airline/icao/$airlineIcao" in {
      (airlineSvc.getAirlineByIcao _)
        .expects(airlineIcao)
        .returning(Future(Some(validAirlineSeq.head)))
      Get(s"/airline/icao/$airlineIcao") ~> airlineRoute ~> check {
        response.status shouldBe OK
        val expected: Airline = validAirlineSeq.head
        val actual: Airline = entityAsType(classOf[Airline])
        expected shouldEqual actual
      }
    }

    s"return 404/ NotFound from GET:/airline/icao/bad" in {
      (airlineSvc.getAirlineByIcao _)
        .expects("XXX")
        .returning(Future(None))
      Get("/airline/icao/XXX") ~> airlineRoute ~> check {
        response.status shouldBe NotFound
      }
    }

    s"return 200/Airline from GET:airline/iata/$airlineIata" in {
      (airlineSvc.getAirlineByIata _)
        .expects(airlineIata)
        .returning(Future(Some(validAirlineSeq.head)))
      Get(s"/airline/iata/$airlineIata") ~> airlineRoute ~> check {
        response.status shouldBe OK
        val expected: Airline = validAirlineSeq.head
        val actual: Airline = entityAsType(classOf[Airline])
        expected shouldEqual actual
      }
    }

    s"return 404/ NotFound from GET:airline/iata/bad" in {
      (airlineSvc.getAirlineByIata _)
        .expects("XX")
        .returning(Future(None))
      Get("/airline/iata/XX") ~> airlineRoute ~> check {
        response.status shouldBe NotFound
      }
    }

    s"return 200/[Airline] from GET:airline/active" in {
      (airlineSvc.getActiveAirlines _)
        .expects()
        .returning(Future(validAirlineSeq))
      Get("/airline/active") ~> airlineRoute ~> check {
        response.status shouldBe OK
        val expected: Seq[Airline] = validAirlineSeq
        val actual: Seq[Airline] = entityAsSeqType(classOf[Airline])
        expected shouldEqual actual
      }
    }

  }

}