package com.grndctl.controllers

import akka.http.scaladsl.server.Route
import com.github.swagger.akka.model.Info
import com.github.swagger.akka.{SwaggerHttpService, model}

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class SwaggerController(address: String, port: Int) extends SwaggerHttpService {
  def swaggerUiRoutes: Route = {
    path("swagger") {
      getFromResource("swagger/index.html")
    } ~ getFromResourceDirectory("swagger")
  }

  override val apiClasses = Set(
    classOf[MetarController]
  )
  override val host: String = s"$address:$port"
  override val info: Info = Info(
    description = "An Aviators API",
    version = "1.0.0-SNAPSHOT",
    title = "grndctl-akka",
    contact = Option(
      model.Contact("Michael DiSalvo", "", "michael.vincent.disalvo@gmail.com")
    ),
    license = None
  )
  override val apiDocsPath: String = "api-docs"
  override val routes: Route = super.routes ~ swaggerUiRoutes
}
