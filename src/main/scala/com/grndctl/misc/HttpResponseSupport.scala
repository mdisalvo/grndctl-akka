package com.grndctl.misc

import akka.http.scaladsl.model.{ContentType, HttpEntity, HttpResponse, MediaTypes}
import com.fasterxml.jackson.databind.ObjectMapper

import scala.collection.JavaConverters._

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
trait HttpResponseSupport {

  val OM: ObjectMapper = new ObjectMapper

  def seqToHttpResponse[T <: Object](s: Seq[T]): HttpResponse =
    HttpResponse(entity = HttpEntity(
        ContentType(MediaTypes.`application/json`),
        OM.writeValueAsString(s.asJava)
      ))

  def seqMultiMapToHttpResponse[T <: Object, U <: Object](m: Map[T, Seq[U]]): HttpResponse =
    HttpResponse(entity = HttpEntity(
        ContentType(MediaTypes.`application/json`),
        OM.writeValueAsString(m.map(e => e._1 -> e._2.asJava).asJava)
      ))

  def anyToHttpResponse(o: Any): HttpResponse =
    HttpResponse(entity = HttpEntity(
        ContentType(MediaTypes.`application/json`),
        OM.writeValueAsString(o)
      ))

}
