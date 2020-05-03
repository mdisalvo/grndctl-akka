package com.grndctl.misc

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{RejectionHandler, Route}
import akka.stream.Materializer
import com.grndctl.controllers._
import com.grndctl.services._

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class Registry(config: Config)(
  implicit val actorSystem: ActorSystem,
  implicit val materializer: Materializer,
  implicit val executionContext: ExecutionContextExecutor
) {
  import ExecutionContexts._

  lazy val stationService: StationSvc = new StationSvc()(svcContext)
  lazy val stationController: StationController =
    new StationController(stationService)(httpContext)

  lazy val metarService: MetarSvc = new MetarSvc()(svcContext)
  lazy val metarController: MetarController =
    new MetarController(metarService)(httpContext)

  lazy val tafService: TafSvc = new TafSvc()(svcContext)
  lazy val tafController: TafController =
    new TafController(tafService)(httpContext)

  lazy val airepService: AirepSvc = new AirepSvc()(svcContext)
  lazy val airepController: AirepController =
    new AirepController(airepService)(httpContext)

  lazy val conversionController: ConversionController = new ConversionController

  val airlineService: AirlineSvc = AirlineSvc.apply(svcContext)
  lazy val airlineController: AirlineController =
    new AirlineController(airlineService)(httpContext)

  val navaidService: NavaidSvc = NavaidSvc.apply(svcContext)
  lazy val navaidController = new NavaidController(navaidService)(httpContext)

  lazy val notamService: NotamSvc = new NotamSvc()(svcContext)
  lazy val notamController: NotamController =
    new NotamController(notamService)(httpContext)

  lazy val docsController: DocsController = new DocsController()(httpContext)

  lazy val server: HttpServer = loadServer()

  private def loadServer(): HttpServer = {
    val routes: Route = handleRejections(RejectionHandler.default) {
      metarController.route ~
        stationController.route ~
        tafController.route ~
        airepController.route ~
        conversionController.route ~
        airlineController.route ~
        navaidController.route ~
        notamController.route ~
        docsController.route
    }
    new HttpServer(routes, config.httpHost, config.httpPort)
  }

  object ExecutionContexts {
    val svcContext: ExecutionContext =
      actorSystem.dispatchers.lookup("service-context")
    val httpContext: ExecutionContext =
      actorSystem.dispatchers.lookup("http-context")
  }

}
