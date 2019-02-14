package com.grndctl.misc

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{RejectionHandler, Route}
import akka.stream.ActorMaterializer
import com.grndctl.controllers._
import com.grndctl.services._

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class Registry(config: Config)
              (implicit val actorSystem: ActorSystem,
               implicit val materializer: ActorMaterializer,
               implicit val executionContext: ExecutionContextExecutor) {

  lazy val stationService: StationSvc = new StationSvc()
  lazy val stationController: StationController = new StationController(stationService)

  lazy val metarService: MetarSvc = new MetarSvc()
  lazy val metarController: MetarController = new MetarController(metarService)

  lazy val tafService: TafSvc = new TafSvc()
  lazy val tafController: TafController = new TafController(tafService)

  lazy val airepService: AirepSvc = new AirepSvc()
  lazy val airepController: AirepController = new AirepController(airepService)

  lazy val conversionController: ConversionController = new ConversionController()

  lazy val chartsService: ChartsSvc = new ChartsSvc()
  lazy val chartsController: ChartsController = new ChartsController(chartsService)

  val airlineService: AirlineSvc = AirlineSvc.apply
  lazy val airlineController: AirlineController = new AirlineController(airlineService)

  val navaidService: NavaidSvc = NavaidSvc.apply
  lazy val navaidController = new NavaidController(navaidService)

  lazy val notamService: NotamSvc = new NotamSvc()
  lazy val notamController: NotamController = new NotamController(notamService)

  lazy val docsController: DocsController = new DocsController

  lazy val server: HttpServer = loadServer()

  private def loadServer(): HttpServer = {
    val routes: Route = handleRejections(RejectionHandler.default) {
        metarController.route      ~
        stationController.route    ~
        tafController.route        ~
        airepController.route      ~
        conversionController.route ~
        chartsController.route     ~
        airlineController.route    ~
        navaidController.route     ~
        notamController.route      ~
        docsController.route
    }
    new HttpServer(routes, config.httpHost, config.httpPort)
  }

  object ExecutionContexts {
    val svcContext: ExecutionContext = actorSystem.dispatchers.lookup("service-context")
    val httpContext: ExecutionContext = actorSystem.dispatchers.lookup("http-context")
    val actorContext: ExecutionContext = actorSystem.dispatchers.lookup("actor-context")
  }

}
