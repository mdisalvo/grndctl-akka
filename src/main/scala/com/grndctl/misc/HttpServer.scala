package com.grndctl.misc

import akka.actor.ActorSystem
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.{Http, HttpConnectionContext}
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}
import akka.stream.Materializer

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class HttpServer(routes: Route, interface: String, port: Int)
                (implicit val actorSystem: ActorSystem,
                 implicit val materializer: Materializer,
                 implicit val executor: ExecutionContextExecutor) extends LazyLogging {

  var serverBinding: Future[ServerBinding] = _

  def startServer(): Unit = {
    serverBinding = Http().bindAndHandle(routes, interface, port, new HttpConnectionContext())
    serverBinding.onComplete {
      case Success(binding) =>
      case Failure(t) => logger.error(s"Failed to start server: ${t.getMessage}")
    }
  }

  def shutdown(): Unit = {
    serverBinding.onComplete {
      case Success(binding) => binding.unbind()
      case Failure(t) => logger.error(s"Failed to shutdown: ${t.getMessage}")
    }
  }

}
