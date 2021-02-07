import sbt._

object Dependencies {

  object Versions {
    lazy val catsCore = "2.2.0"
    lazy val config = "1.4.1"
    lazy val scalaLogging = "3.9.2"
    lazy val slf4jApi = "1.7.28"
    lazy val logback = "1.2.3"
    lazy val arm = "2.0"
    lazy val scalaTest = "3.2.2"
    lazy val mockito = "1.5.17"
  }

  object Libraries {
    lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % Versions.scalaLogging
    lazy val slf4jApi = "org.slf4j" % "slf4j-api" % Versions.slf4jApi
    lazy val logback = "ch.qos.logback" % "logback-classic" % Versions.logback
    lazy val config = "com.typesafe" % "config" % Versions.config

    lazy val catsCore = "org.typelevel" %% "cats-core" % Versions.catsCore
    lazy val arm = "com.jsuereth" %% "scala-arm" % Versions.arm
    lazy val scalactic = "org.scalactic" %% "scalactic" % Versions.scalaTest

    // OLD
    lazy val scalaz = "org.scalaz" %% "scalaz-core" % "7.2.26"
    lazy val mpilquist = "com.github.mpilquist" %% "simulacrum" % "0.13.0"
    lazy val json4s = "org.json4s" %% "json4s-jackson" % "3.6.6"
    lazy val shapeless = "com.chuusai" %% "shapeless" % "2.3.3"
    lazy val sttp = "com.softwaremill.sttp" %% "core" % "1.7.2"
  }

  object TestDeps {
    lazy val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest
    lazy val mockito = "org.mockito" %% "mockito-scala" % Versions.mockito % "test"
  }

}



