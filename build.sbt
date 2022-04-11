scalaVersion := "2.13.8"

name := "otel-auto-scala-poc"
organization := "ca.vgorcinschi"
version := "1.0"

lazy val ourMainClass = "ca.gorcinschi.SimpleOtelApp"

lazy val logbackVersion = "1.2.11"
lazy val otelVersion = "1.12.0"
lazy val otelNonStableVersion = "1.12.0-alpha"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "ch.qos.logback" % "logback-core" % logbackVersion,
  "com.softwaremill.macwire" % "macros_2.13" % "2.5.6",
  "com.typesafe" % "config" % "1.4.2",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
  "io.opentelemetry" % "opentelemetry-api" % otelVersion,
  "io.opentelemetry" % "opentelemetry-extension-annotations" % otelVersion,
  "io.opentelemetry.instrumentation" % "opentelemetry-logback-mdc-1.0" % otelNonStableVersion,
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1",
  "org.slf4j" % "slf4j-api" % "1.7.36"
)

Compile / mainClass := Some(ourMainClass)

lazy val runWithOtel = taskKey[Unit]("Run application with observability.")
runWithOtel / fork := true
runWithOtel / javaOptions ++= Seq(
  "-javaagent:src/main/resources/opentelemetry-javaagent-all-v1.12.0.jar",
  "-Dotel.javaagent.configuration-file=src/main/resources/otel.properties"
)
fullRunTask(runWithOtel, Compile, ourMainClass)

