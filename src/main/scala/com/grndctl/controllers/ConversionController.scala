package com.grndctl.controllers

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.grndctl.misc.{ConversionUtility, HttpResponseSupport}
import com.typesafe.scalalogging.LazyLogging


/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class ConversionController extends ConversionUtility with HttpResponseSupport with LazyLogging {

  val route: Route =
    pathPrefix("conversions") {
      pathPrefix("FtoC") {
        parameter('tempF.as[Double]) { tempF =>
          complete {
            ToResponseMarshallable.apply(anyToHttpResponse(fToC(tempF)))
          }
        }
      } ~
        pathPrefix("CtoF") {
          parameter('tempC.as[Double]) { tempC =>
            complete {
              ToResponseMarshallable.apply(anyToHttpResponse(cToF(tempC)))
            }
          }
        } ~
        pathPrefix("inchesToMillibars") {
          parameter('pressInches.as[Double]) { pressInches =>
            complete {
              ToResponseMarshallable.apply(anyToHttpResponse(inchesToMillibars(pressInches)))
            }
          }
        } ~
        pathPrefix("millibarsToInches") {
          parameter('pressMillis.as[Double]) { pressMillis =>
            complete {
              ToResponseMarshallable.apply(anyToHttpResponse(millibarsToInches(pressMillis)))
            }
          }
        } ~
        pathPrefix("windcomponent") {
          parameters('windspeed.as[Double], 'winddirection.as[Double], 'heading.as[Double]) { (windspeed, winddirection, heading) =>
            complete {
              ToResponseMarshallable.apply(
                anyToHttpResponse(windComponents(windspeed, winddirection, heading))
              )
            }
          }
        }
    }

}
