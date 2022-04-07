package ca.gorcinschi

import ca.gorcinschi.configuration.ConfigModule
import ca.gorcinschi.observability.ObservabilityModule
import com.typesafe.scalalogging.LazyLogging
import io.opentelemetry.api.common.AttributeKey.stringKey
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.metrics.Meter

import java.util.UUID
import scala.collection.immutable.LazyList.continually

object SimpleOtelApp extends App with ObservabilityModule with ConfigModule with LazyLogging {

  val meter = opentelemetry.getMeter(config.getConfig.getString("observability.service-name"))

  def recordBeat(meter: Meter, value: Long): Unit = {
    val longCounterBuilder = meter
      .counterBuilder("AppCounter")
    logger.info(s"LongCounterBuilder implementation is $longCounterBuilder")
    longCounterBuilder
      .setUnit("1")
      .setDescription("An example of counter")
      .buildWithCallback(measurement =>
        measurement.record(value, Attributes.of(stringKey("some-attribute"), UUID.randomUUID().toString))
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
