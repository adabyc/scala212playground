import Dependencies._

lazy val playground = (project in file("playground")).settings(
  inThisBuild(
    List(
      organization := "ab.playground",
      scalaVersion := "2.12.12",
      version := "0.1"
    )),
  name := "Playground",
  scalacOptions += "-Ypartial-unification",
  scalacOptions += "-language:_",
  //  assemblyOutputPath in assembly := file("JobRunner.jar"),
  //  test in assembly := {},
  Test / run / fork := true,
  libraryDependencies ++= Seq(
    Libraries.config,
    Libraries.scalaLogging,
    Libraries.slf4jApi,
    Libraries.logback,
    Libraries.arm,
    Libraries.scalaz,
    TestDeps.scalaTest,
    TestDeps.mockito
  )
)

lazy val catz = (project in file("catz")).settings(
  inThisBuild(
    List(
      organization := "ab.catz",
      scalaVersion := "2.12.12",
      version := "0.1"
    )),
  name := "Catz",
  scalacOptions += "-Ypartial-unification",
  scalacOptions += "-language:_",
  //  assemblyOutputPath in assembly := file("JobRunner.jar"),
  //  test in assembly := {},
  Test / run / fork := true,
  libraryDependencies ++= Seq(
    Libraries.catsCore,
    Libraries.config,
    Libraries.scalaLogging,
    Libraries.slf4jApi,
    Libraries.logback,
    Libraries.arm,
    TestDeps.scalaTest,
    TestDeps.mockito
  )
)

