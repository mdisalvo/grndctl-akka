package com.grndctl.services

import java.net.URL

import com.typesafe.scalalogging.LazyLogging
import javax.xml.bind.JAXBContext

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
abstract class InputStreamUnmarshaller[T](respType: Class[T]) extends LazyLogging {

  def unmarshall(url: URL)(implicit ec: ExecutionContext): Future[T] = Future {
    autoClose(url.openStream()) { is =>
      val context: JAXBContext = JAXBContext.newInstance(respType)
      context.createUnmarshaller().unmarshal(is).asInstanceOf[T]
    }.get
  }

  // https://stackoverflow.com/questions/39866000/java-try-with-resource-not-working-with-scala
  private def autoClose[A <: AutoCloseable, B](closeable: A)(fun: A ⇒ B): Try[B] = {
    Try(fun(closeable)).transform(
      result => {
        closeable.close()
        Success(result)
      },
      funT => {
        Try(closeable.close()).transform(
          _ => Failure(funT),
          closeT => {
            logger.error("Exception on close of resource.", closeT)
            funT.addSuppressed(closeT)
            Failure(funT)
          }
        )
      }
    )
  }

}


