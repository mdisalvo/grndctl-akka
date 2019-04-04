package com.grndctl.controllers

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import scala.concurrent.ExecutionContext

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class DocsController(implicit ec: ExecutionContext) {
  val route: Route = 
    pathPrefix("index.html" | "apidocs") {
      getFromResource("APIDocs.html")
    }
}