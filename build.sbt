name := "quiet-slf4j"

organization := "com.dadrox"

version := "0.1"

libraryDependencies += "junit" % "junit-dep" % "4.10"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.0" % "provided"

crossPaths := false

autoScalaLibrary := false

javacOptions in compile ++= List("-target", "1.5", "-source", "1.5")
