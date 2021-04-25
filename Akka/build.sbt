name := "Akka"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % "2.6.14",
  "com.typesafe.akka" %% "akka-slf4j" % "2.6.14",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)
