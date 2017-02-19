organization := "org.craftycoder"
name := "FinanceKata"

version := "1.0"

scalaVersion := "2.12.1"
scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8", "-target:jvm-1.8", "-Xfatal-warnings", "-Xfuture")

libraryDependencies ++=
  Seq(
    "org.scalatest" %% "scalatest" % "3.0.1" % Test
  )

