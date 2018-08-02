package com.grndctl.misc

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
trait AutoClose {

  def autoClose[A <: AutoCloseable,B](closeable: A)(func: A => B): B = {
    try {
      func(closeable)
    } finally {
      closeable.close()
    }
  }

}
