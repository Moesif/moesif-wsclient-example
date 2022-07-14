import Dependencies._

//ThisBuild / scalaVersion     := "2.11.12"
//ThisBuild / scalaVersion     := "2.12.16"
ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / organization     := "com.moesif"
ThisBuild / organizationName := "Moesif"

//libraryDependencies += "com.typesafe.play" %% "play-ahc-ws" % "2.7.9"
libraryDependencies += "com.typesafe.play" %% "play-ahc-ws" % "2.8.16"

// test dependencies
lazy val root = (project in file("."))
  .settings(
    name := "moesif-wsclient-example",
    libraryDependencies += scalaTest % Test
  )
libraryDependencies += "org.junit.jupiter" % "junit-jupiter-api" % "5.8.2" % Test
crossPaths := false
libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

