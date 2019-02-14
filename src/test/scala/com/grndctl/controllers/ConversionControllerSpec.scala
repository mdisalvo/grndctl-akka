package com.grndctl.controllers

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Route
import com.grndctl.BaseSpec
import com.grndctl.misc.ConversionUtility
import com.grndctl.model.aggregates.{ConversionResult, WindComponent}

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class ConversionControllerSpec extends BaseSpec with ConversionUtility {

  val convController = new ConversionController
  val convRoute = convController.route

  "The Conversion Controller" should {

    s"return 200/ConversionResult from GET:/conversions/FtoC?tempF=33.2" in {
      Get("/conversions/FtoC?tempF=33.2") ~> convRoute ~> check {
        response.status shouldBe OK
        val expected: ConversionResult = fToC(33.2)
        val actual: ConversionResult = entityAsType(classOf[ConversionResult])
        expected shouldEqual actual
      }
    }

    s"return 400/ BadRequest from GET:/conversions/FtoC?tempF=tempF" in {
      Get("/conversions/FtoC?tempF=tempF") ~> Route.seal(convRoute) ~> check {
        response.status shouldBe BadRequest
      }
    }

    s"return 200/ConversionResult from GET:/conversions/CtoF?tempC=0.0" in {
      Get("/conversions/CtoF?tempC=0.0") ~> convRoute ~> check {
        response.status shouldBe OK
        val expected: ConversionResult = cToF(0.0)
        val actual: ConversionResult = entityAsType(classOf[ConversionResult])
        expected shouldEqual actual
      }
    }

    s"return 400/ BadRequst from GET:/conversions/CtoF?tempC=tempC" in {
      Get("/conversions/CtoF?tempC=tempC") ~> Route.seal(convRoute) ~> check {
        response.status shouldBe BadRequest
      }
    }

    s"return 200/ConversionResult from GET:/conversions/inchesToMillibars?pressInches=29.92" in {
      Get("/conversions/inchesToMillibars?pressInches=29.92") ~> convRoute ~> check {
        response.status shouldBe OK
        val expected: ConversionResult = inchesToMillibars(29.92)
        val actual: ConversionResult = entityAsType(classOf[ConversionResult])
        expected shouldEqual actual
      }
    }

    s"return 400/ BadRequest from GET:/conversions/inchesToMillibars?pressInches=pressInches" in {
      Get("/conversions/inchesToMillibars?pressInches=pressInches") ~> Route.seal(convRoute) ~> check {
        response.status shouldBe BadRequest
      }
    }

    s"return 200/ConversionResult from GET:/conversions/millibarsToInches?pressMillis=1013.3" in {
      Get("/conversions/millibarsToInches?pressMillis=1013.3") ~> convRoute ~> check {
        response.status shouldBe OK
        val expected: ConversionResult = millibarsToInches(1013.3)
        val actual: ConversionResult = entityAsType(classOf[ConversionResult])
        expected shouldEqual actual
      }
    }

    s"return 400/ BadRequest from GET:/conversions/millibarsToInches?pressMillis=pressMillis" in {
      Get("/conversions/millibarsToInches?pressMillis=pressMillis") ~> Route.seal(convRoute) ~> check {
        response.status shouldBe BadRequest
      }
    }

    s"return 200/WindComponent from GET:/conversions/windcomponent?windspeed=10&winddirection=260&heading=200" in {
      Get("/conversions/windcomponent?windspeed=10&winddirection=260&heading=200") ~> convRoute ~> check {
        response.status shouldBe OK
        val expected: WindComponent = windComponents(10, 260, 200)
        val actual: WindComponent = entityAsType(classOf[WindComponent])
        expected shouldEqual actual
      }
    }

    s"return 400/ BadRequest from GET:/conversions/windcomponent?windspeed=ws&winddirection=wd&heading=h" in {
      Get("/conversions/windcomponent?windspeed=ws&winddirection=wd&heading=h") ~> Route.seal(convRoute) ~> check {
        response.status shouldBe BadRequest
      }
    }

  }

}
