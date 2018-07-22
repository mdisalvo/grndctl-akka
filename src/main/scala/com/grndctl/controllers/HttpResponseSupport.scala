package com.grndctl.controllers

import akka.http.scaladsl.model.{ContentType, HttpEntity, HttpResponse, MediaTypes}
import com.fasterxml.jackson.databind.ObjectMapper

import scala.collection.JavaConverters._

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
trait HttpResponseSupport {

  val OM: ObjectMapper = new ObjectMapper

  def seqToHttpResponse[A <: Object](seq: Seq[A]): HttpResponse = {
    HttpResponse(
      entity = HttpEntity(
        ContentType(MediaTypes.`application/json`),
        OM.writeValueAsString(seq.asJava)
      )
    )
  }

  def anyToHttpResponse(o: Any): HttpResponse = {
    HttpResponse(
      entity = HttpEntity(
        ContentType(MediaTypes.`application/json`),
        OM.writeValueAsString(o)
      )
    )
  }

}
