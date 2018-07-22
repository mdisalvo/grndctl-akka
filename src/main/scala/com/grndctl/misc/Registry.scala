package com.grndctl.misc

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{RejectionHandler, Route}
import akka.stream.ActorMaterializer
import com.grndctl.controllers.{MetarController, SwaggerController}
import com.grndctl.services.MetarSvc

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

/**
  * Responsible for instantiating dependent classes.  Roll your own DI.
  *
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class Registry(config: Config)
              (implicit val actorSystem: ActorSystem,
               implicit val materializer: ActorMaterializer,
               implicit val executionContext: ExecutionContextExecutor) {

  lazy val metarService: MetarSvc = new MetarSvc()
  lazy val metarController: MetarController = new MetarController(metarService)

  lazy val server: HttpServer = loadServer()

  private def loadServer(): HttpServer = {
    val swaggerRoutes: Route = new SwaggerController(config.httpHost, config.httpPort).routes
    val routes: Route = handleRejections(RejectionHandler.default) {
      swaggerRoutes ~ metarController.route
    }
    new HttpServer(routes, config.httpHost, config.httpPort)
  }

  object ExecutionContexts {
    val svcContext: ExecutionContext = actorSystem.dispatchers.lookup("service-context")
    val httpContext: ExecutionContext = actorSystem.dispatchers.lookup("http-context")
    val actorContext: ExecutionContext = actorSystem.dispatchers.lookup("actor-context")
  }

}
