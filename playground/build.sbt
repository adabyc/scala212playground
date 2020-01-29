//name := "scala212playground"

version := "0.1"

scalaVersion := "2.12.6"
scalacOptions += "-Ypartial-unification"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5", // % "test",
  "com.jsuereth" %% "scala-arm" % "2.0",
  "org.typelevel" %% "cats-core" % "1.0.1",
  "com.typesafe" % "config" % "1.3.2",
  "com.chuusai" %% "shapeless" % "2.3.3",
  "org.json4s" %% "json4s-jackson" % "3.6.6",
  "com.softwaremill.sttp" %% "core" % "1.7.2"

)

scalacOptions in ThisBuild ++= Seq(
  "-language:_",
  "-Ypartial-unification",
  //  "-Xfatal-warnings"
)

libraryDependencies ++= Seq(
  "com.github.mpilquist" %% "simulacrum" % "0.13.0",
  "org.scalaz" %% "scalaz-core" % "7.2.26"
)

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.7")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)