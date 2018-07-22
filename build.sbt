// Logging and config
val log = "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
val config = "com.typesafe" % "config" % "1.3.1"
val common: Seq[ModuleID] = Seq(log, logback, config)

// Akka
val akkaHttp = "com.typesafe.akka" %% "akka-http" % "10.1.3"
val testKit = "com.typesafe.akka" %% "akka-http-testkit" % "10.1.3"
val sprayJson = "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.3"
val akkaActor = "com.typesafe.akka" %% "akka-actor" % "2.5.14"
val akka: Seq[ModuleID] = Seq(akkaHttp, testKit, sprayJson)

// Test
val scalatest = "org.scalatest" %% "scalatest" % "3.0.4"
val scalamock = "org.scalamock" %% "scalamock" % "4.1.0"
val test: Seq[ModuleID] = Seq(scalatest, scalamock)

// Swagger
val swagger = "com.github.swagger-akka-http" %% "swagger-akka-http" % "0.14.1"
val swaggerUi = "org.webjars" % "swagger-ui" % "3.17.0"
val swag: Seq[ModuleID] = Seq(swagger)

lazy val grndctl = (project in file("."))
  .settings(
    organization := "com.grndctl",
    version := "1.0.0-SNAPSHOT",
    name := "grndctl-akka",
    description := """An Aviators API""",
    scalaVersion := "2.12.5",
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
    libraryDependencies ++= common ++ akka ++ test ++ swag
  )

mainClass in Compile := Some("com.grndctl.Grndctl")
