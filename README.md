# Quiet slf4j

An slf4j logger that, by default, logs nothing.

This is really intended to suppress log output during testing.

It is written in Java, so should be usable almost anywhere.

## Usage

In order for this to really work, you need to set all of your other slf4 log binders to no be available during test.

## Dependency

### SBT

#### build.sbt

    libraryDependencies ++= Seq(
        "org.slf4j" % "slf4j-api" % "1.7.0",
        "ch.qos.logback" % "logback-classic" % "1.0.0" % "!test",
        "com.dadrox" % "quiet-slf4j" % "0.4" % "test")

This way you get the quiet logger while running tests and the real logger in every other context.

## Configuration 

To enable more logging than none, use the system property log.level and give it the desired log level:

off, trace, debug, info, warn. error, or all

You can also just use the first letter or fragment of the level (case insensitive) in the system property.

## Examples

    > mvn -Dlog.level=debug test

    > sbt -Dlog.level=e test

Yes, it really is that easy!

## License

Copyright (C) 2012-2013, Christopher Wood (dadrox)

Published under [BSD 2-Clause License](http://opensource.org/licenses/BSD-2-Clause)

