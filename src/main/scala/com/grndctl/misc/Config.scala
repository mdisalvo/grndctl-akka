package com.grndctl.misc

import com.typesafe.config.ConfigFactory

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
class Config(val config: com.typesafe.config.Config = ConfigFactory.load()) {
  val httpHost: String = config.getString("http.interface")
  val httpPort: Int = config.getInt("http.port")
}
