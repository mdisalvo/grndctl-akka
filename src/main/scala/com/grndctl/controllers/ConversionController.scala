package com.grndctl.controllers

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.grndctl.misc.HttpResponseSupport
import com.grndctl.model.aggregates.{ConversionResult, WindComponent}
import com.typesafe.scalalogging.LazyLogging
import org.apache.commons.lang3.math.Fraction._


/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class ConversionController extends HttpResponseSupport with LazyLogging {

  object UnitOfMeasure extends Enumeration {
    val Celsius         = Value("C")
    val Fahrenheit      = Value("F")
    val Millibars       = Value("mb")
    val InchesOfMercury = Value("inHg")
  }

  private val ConvFactor: Double = 33.8639

  val route: Route =
    pathPrefix("conversions") {
      pathPrefix("FtoC") {
        parameter('tempF) { tempF =>
          fToC(tempF.toDouble)
        }
      } ~
      pathPrefix("CtoF") {
        parameter('tempC) { tempC =>
          CtoF(tempC.toDouble)
        }
      } ~
      pathPrefix("inchesToMillibars") {
        parameter('pressInches) { pressInches =>
          inchesToMillibars(pressInches.toDouble)
        }
      } ~
      pathPrefix("millibarsToInches") {
        parameter('pressMillis) { pressMillis =>
          millibarsToInches(pressMillis.toDouble)
        }
      } ~
      pathPrefix("windcomponent") {
        parameters('windspeed, 'winddirection, 'heading) { (windspeed, winddirection, heading) =>
          windComponents(windspeed.toDouble, winddirection.toDouble, heading.toDouble)
        }
      }
    }

  def fToC(tempF: Double): Route = complete {
    ToResponseMarshallable.apply(anyToHttpResponse(
      new ConversionResult((tempF - 32) * getFraction(5, 9).doubleValue(), UnitOfMeasure.Celsius.toString)
    ))
  }

  def CtoF(tempC: Double): Route = complete {
    ToResponseMarshallable.apply(anyToHttpResponse(
      new ConversionResult(tempC * 1.8 + 32, UnitOfMeasure.Fahrenheit.toString)
    ))
  }

  def inchesToMillibars(pressInches: Double): Route = complete {
    ToResponseMarshallable.apply(anyToHttpResponse(
      new ConversionResult(ConvFactor * pressInches, UnitOfMeasure.Millibars.toString)
    ))
  }

  def millibarsToInches(pressMillis: Double): Route = complete {
    ToResponseMarshallable.apply(anyToHttpResponse(
      new ConversionResult(pressMillis / ConvFactor, UnitOfMeasure.InchesOfMercury.toString)
    ))
  }

  def windComponents(windSpeed: Double, windDirection: Double, heading: Double): Route = complete {
    ToResponseMarshallable.apply(anyToHttpResponse(
      new WindComponent(windSpeed, windDirection, heading)
    ))
  }

}
