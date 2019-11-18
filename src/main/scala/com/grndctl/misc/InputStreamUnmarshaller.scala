package com.grndctl.misc

import java.net.URL

import com.fasterxml.jackson.databind.ObjectMapper
import com.typesafe.scalalogging.LazyLogging
import javax.xml.bind.JAXBContext

import scala.concurrent.{ExecutionContext, Future}

/**
  * I realize that this is an unorthodox way to go about my business.
  *
  * I decided to re-use Java domain classes from the spring booted version of this app for two reasons.
  *
  * 1. Laziness
  * 2. It gave me an opportunity to write some supporting traits with generics.
  *
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
abstract class InputStreamUnmarshaller[T](respType: Class[T])
    extends LazyLogging
    with AutoClose {

  val OM: ObjectMapper = new ObjectMapper

  protected val ErrorMessage: String =
    "Exception when unmarshalling entity from ADDS Service."

  def unmarshall(url: URL)(implicit ec: ExecutionContext): Future[T] = Future {
    autoClose(url.openStream()) {
      val context: JAXBContext = JAXBContext.newInstance(respType)
      context.createUnmarshaller().unmarshal(_).asInstanceOf[T]
    }
  }

}
