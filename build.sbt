scalaVersion := "2.13.8"

name := "otel-auto-scala-poc"
organization := "ca.vgorcinschi"
version := "1.0"

lazy val ourMainClass = "ca.gorcinschi.SimpleOtelApp"

lazy val otelVersion = "1.12.0"
lazy val otelNonStableVersion = "1.12.0-alpha"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1",
  "io.opentelemetry" % "opentelemetry-api" % otelVersion,
  "io.opentelemetry" % "opentelemetry-extension-annotations" % otelVersion,
  "io.opentelemetry.instrumentation" % "opentelemetry-logback-mdc-1.0" % otelNonStableVersion,
  "com.softwaremill.macwire" % "macros_2.13" % "2.5.6",
  "com.typesafe" % "config" % "1.4.2"
)

Compile / mainClass := Some(ourMainClass)