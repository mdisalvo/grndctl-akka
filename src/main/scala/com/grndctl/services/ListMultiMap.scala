package com.grndctl.services

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class ListMultiMap[A, B] {

  private val m = new mutable.HashMap[A, ListBuffer[B]]

  def put(a: A, b: B): ListMultiMap[A, B] = put(a, List(b): _*)

  def put(a: A, b: B*): ListMultiMap[A, B] = {
    if (m.get(a).isEmpty) m.put(a, new ListBuffer[B])
    m(a).appendAll(b)
    this
  }

  def toMap: Map[A, Seq[B]] = m.map(e => e._1 -> e._2).toMap

  override def toString: String =
    m.map { case (k, v) => (k, v.toList.mkString("[", ", ", "]")) }.toString

}
