name := "scala212playground"

version := "0.1"

scalaVersion := "2.12.6"
scalacOptions += "-Ypartial-unification"

lazy val root = (project in file("."))
    .aggregate(playground)
    .settings(
      name := "playground"
    )

lazy val playground = (project in file("playground"))
    .settings(
      name := "playground"
    )

