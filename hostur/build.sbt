name := "hostur"

version := "1.0"

scalaVersion := "2.11.8"

exportJars := true

unmanagedClasspath in Runtime += baseDirectory.value / "plugins"

libraryDependencies += "org.clapper" %% "classutil" % "1.0.8"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3"

// libraryDependencies += "json" %% "json" % "1.0"
