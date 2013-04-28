name := "quiet-slf4j"

organization := "com.dadrox"

version := "0.1"

libraryDependencies ++= Seq(
	"junit" % "junit-dep" % "4.10" % "test->default",
	"org.slf4j" % "slf4j-api" % "1.7.0" % "provided",
	"com.dadrox" % "sbt-junit" % "0.3" % "test")

testFrameworks += new TestFramework("com.dadrox.sbt.junit.JunitFramework")

crossPaths := false

autoScalaLibrary := false

javacOptions in compile ++= List("-target", "1.5", "-source", "1.5")
