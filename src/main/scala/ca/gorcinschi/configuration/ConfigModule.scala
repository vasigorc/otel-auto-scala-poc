package ca.gorcinschi.configuration

import com.softwaremill.macwire.wire
import com.typesafe.config.{ConfigFactory, Config => TSConfig}

final class Config {
  lazy private val root = ConfigFactory.load
  lazy private val envConfig = root.getConfig(sys.env.getOrElse("OUR_APP_ENV", "default"))
  def getConfig: TSConfig = envConfig
}

trait ConfigModule {
  val config: Config = wire[Config]
}
