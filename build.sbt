name := "Japan crossword"

version := "1.0"

scalaVersion := "2.12.4"
mainClass in (Compile, run) := Some("ru.onehalf.japancrossword.Main")

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "org.scalamock" %% "scalamock" % "4.1.0" % Test
