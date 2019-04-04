// Logging and config
val log = "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
val logbackClassic = "ch.qos.logback" % "logback-classic" % "1.2.3"
val logbackCore = "ch.qos.logback" % "logback-core" % "1.2.3"
val config = "com.typesafe" % "config" % "1.3.1"
val apacheCommons = "org.apache.commons" % "commons-lang3" % "3.8.1"
val guava = "com.google.guava" % "guava" % "23.0"
val common: Seq[ModuleID] = Seq(log, logbackClassic, logbackCore, config, apacheCommons, guava)

// Akka/HTTP
val akkaHttp = "com.typesafe.akka" %% "akka-http" % "10.1.3"
val testKit = "com.typesafe.akka" %% "akka-http-testkit" % "10.1.3"
val jackson = "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.7"
val json = "org.json" % "json" % "20180813"
val jsoup = "org.jsoup" % "jsoup" % "1.11.3"

val akka: Seq[ModuleID] = Seq(akkaHttp, testKit, jackson, json, jsoup)

// Test
val scalatest = "org.scalatest" %% "scalatest" % "3.0.4"
val scalamock = "org.scalamock" %% "scalamock" % "4.1.0"
val test: Seq[ModuleID] = Seq(scalatest, scalamock)

maintainer in Docker := "Michael DiSalvo <michael.vincent.disalvo@gmail.com>"
packageSummary in Docker := "An Aviators Container"
dockerBaseImage := "openjdk:8u171-jdk"
dockerRepository := Some("michaelvdisalvo")

lazy val grndctl = (project in file("."))
  .settings(
    organization := "com.grndctl",
    version := "1.0.1",
    name := "grndctl-akka",
    description := """An Aviators API""",
    scalaVersion := "2.12.5",
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
    libraryDependencies ++= common ++ akka ++ test
  ).enablePlugins(JavaAppPackaging)

mainClass in Compile := Some("com.grndctl.Grndctl")
