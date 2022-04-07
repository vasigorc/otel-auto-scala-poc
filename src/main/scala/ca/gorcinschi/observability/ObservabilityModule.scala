package ca.gorcinschi.observability

import com.softwaremill.macwire.wire
import com.typesafe.scalalogging.LazyLogging
import io.opentelemetry.api.metrics.Meter
import io.opentelemetry.api.{GlobalOpenTelemetry, OpenTelemetry}

final class OpentelemetryImplementation extends LazyLogging {
  private lazy val opentelemetry: OpenTelemetry = GlobalOpenTelemetry.get()
  logger.info(s"Opentelemetry Context Propagators: ${opentelemetry.getPropagators}")
  def getMeter(name: String): Meter = opentelemetry.getMeter(name)
}

trait ObservabilityModule {
  lazy val opentelemetry: OpentelemetryImplementation = wire[OpentelemetryImplementation]
}
