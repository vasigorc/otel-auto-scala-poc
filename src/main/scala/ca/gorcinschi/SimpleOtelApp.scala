package ca.gorcinschi

import ca.gorcinschi.configuration.ConfigModule
import ca.gorcinschi.helpers.UUIDGenerator
import ca.gorcinschi.observability.ObservabilityModule
import com.typesafe.scalalogging.LazyLogging
import io.opentelemetry.api.common.AttributeKey.stringKey
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.metrics.Meter

import scala.collection.immutable.LazyList.continually

object SimpleOtelApp extends App with ObservabilityModule with ConfigModule with LazyLogging with UUIDGenerator {

  val meter = opentelemetry.getMeter(config.getConfig.getString("observability.service-name"))
  logger.info(s"Meter implementation is $meter")

  def recordBeat(meter: Meter, value: Long): Unit = {
    meter
      .counterBuilder("AppCounter")
      .setUnit("1")
      .setDescription("An example of counter")
      .buildWithCallback(measurement =>
        measurement.record(value, Attributes.of(stringKey("some-attribute"), stringifiedUUID()))
      )
  }

  var beat: Long = 0

  for (n <- continually({
      beat += 1; Thread.sleep(1000)
      beat
    })) {
    recordBeat(meter, n)
  }
}
