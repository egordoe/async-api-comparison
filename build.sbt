name := "futures-api-comparison"

version := "1.0"

scalaVersion := "2.11.8"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

crossPaths := false

// http://mvnrepository.com/artifact/io.reactivex/rxjava
libraryDependencies += "io.reactivex" % "rxjava" % "1.1.5"

// http://mvnrepository.com/artifact/org.apache.commons/commons-lang3
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.4"

