// Logging and config
val log = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
val logbackClassic = "ch.qos.logback" % "logback-classic" % "1.2.3"
val logbackCore = "ch.qos.logback" % "logback-core" % "1.2.3"
val config = "com.typesafe" % "config" % "1.4.0"
val apacheCommons = "org.apache.commons" % "commons-lang3" % "3.9"
val guava = "com.google.guava" % "guava" % "28.2-jre"
val common: Seq[ModuleID] =
  Seq(log, logbackClassic, logbackCore, config, apacheCommons, guava)

// Akka/HTTP
val akkaHttp = "com.typesafe.akka" %% "akka-http" % "10.1.11"
val akkaActor = "com.typesafe.akka" %% "akka-actor" % "2.6.3"
val akkaStream = "com.typesafe.akka" %% "akka-stream" % "2.6.3"
val testKit = "com.typesafe.akka" %% "akka-http-testkit" % "10.1.11"
val jackson = "com.fasterxml.jackson.core" % "jackson-databind" % "2.10.2"
val json = "org.json" % "json" % "20190722"
val jsoup = "org.jsoup" % "jsoup" % "1.12.1"

val akka: Seq[ModuleID] =
  Seq(akkaHttp, akkaActor, akkaStream, testKit, jackson, json, jsoup)

// Test
val scalatest = "org.scalatest" %% "scalatest" % "3.1.0" % Test
val scalamock = "org.scalamock" %% "scalamock" % "4.4.0" % Test
val akkaTestKit = "com.typesafe.akka" %% "akka-testkit" % "2.6.3" % Test
val test: Seq[ModuleID] = Seq(scalatest, scalamock, akkaTestKit)

maintainer in Docker := "Michael DiSalvo <michael.vincent.disalvo@gmail.com>"
packageSummary in Docker := "An Aviators Container"
dockerBaseImage := "openjdk:jdk-oraclelinux7"
dockerRepository := Some("michaelvdisalvo")
dockerUpdateLatest := true

lazy val grndctl = (project in file("."))
  .settings(
    organization := "com.grndctl",
    version := "2.0.0",
    name := "grndctl-akka",
    description := """An Aviators API""",
    scalaVersion := "2.12.10",
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
    libraryDependencies ++= common ++ akka ++ test
  )
  .enablePlugins(JavaAppPackaging)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x                             => MergeStrategy.first
}

scalacOptions ++= Seq("-deprecation", "-feature")

mainClass in Compile := Some("com.grndctl.Grndctl")
