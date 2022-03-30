package ca.gorcinschi.observability

import com.softwaremill.macwire.wire
import io.opentelemetry.api.metrics.Meter
import io.opentelemetry.api.{GlobalOpenTelemetry, OpenTelemetry}

final class OpentelemetryImplementation {
  private lazy val opentelemetry: OpenTelemetry = GlobalOpenTelemetry.get()
  def getMeter(name: String): Meter = opentelemetry.getMeter(name)
}

trait ObservabilityModule {
  lazy val opentelemetry: OpentelemetryImplementation = wire[OpentelemetryImplementation]
}
