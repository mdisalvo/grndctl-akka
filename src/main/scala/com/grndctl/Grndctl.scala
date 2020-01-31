package com.grndctl

import akka.actor.ActorSystem
import com.grndctl.misc.{Config, Registry}
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContextExecutor

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class Grndctl(registry: Registry) {
  registry.server.startServer()

  def shutdown(): Unit = {
    registry.server.shutdown()
  }
}

object Grndctl extends LazyLogging {
  def main(args: Array[String]): Unit = {
    implicit val actorSystem: ActorSystem = ActorSystem("grndctl")
    implicit val executor: ExecutionContextExecutor = actorSystem.dispatcher

    val registry = new Registry(new Config())
    val grndctl = new Grndctl(registry)

    scala.sys.addShutdownHook {
      grndctl.shutdown()
      actorSystem.terminate()
    }
  }
}
