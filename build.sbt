// Logging and config
val log = "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
val logbackClassic = "ch.qos.logback" % "logback-classic" % "1.2.3"
val logbackCore = "ch.qos.logback" % "logback-core" % "1.2.3"
val config = "com.typesafe" % "config" % "1.3.1"
val common: Seq[ModuleID] = Seq(log, logbackClassic, logbackCore, config)

// Akka/HTTP
val akkaHttp = "com.typesafe.akka" %% "akka-http" % "10.1.3"
val testKit = "com.typesafe.akka" %% "akka-http-testkit" % "10.1.3"
val json = "org.json" % "json" % "20180813"
val jsoup = "org.jsoup" % "jsoup" % "1.11.3"

val akka: Seq[ModuleID] = Seq(akkaHttp, testKit, json, jsoup)

// Test
val scalatest = "org.scalatest" %% "scalatest" % "3.0.4"
val scalamock = "org.scalamock" %% "scalamock" % "4.1.0"
val test: Seq[ModuleID] = Seq(scalatest, scalamock)

val swagger = "com.github.swagger-akka-http" %% "swagger-akka-http" % "0.14.1"
val swag: Seq[ModuleID] = Seq(swagger)

maintainer in Docker := "Michael DiSalvo <michael.vincent.disalvo@gmail.com>"
packageSummary in Docker := "An Aviators Container"
dockerBaseImage := "openjdk:8u171-jdk"
dockerRepository := Some("michaelvdisalvo")

lazy val grndctl = (project in file("."))
  .settings(
    organization := "com.grndctl",
    version := "1.0.0-SNAPSHOT",
    name := "grndctl-akka",
    description := """An Aviators API""",
    scalaVersion := "2.12.5",
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
    libraryDependencies ++= common ++ akka ++ test ++ swag
  ).enablePlugins(JavaAppPackaging)

mainClass in Compile := Some("com.grndctl.Grndctl")
