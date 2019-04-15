name := "Hashtag-Correlation"

version := "0.1"

scalaVersion := "2.11.8"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.0"

// https://mvnrepository.com/artifact/org.apache.spark/spark-streaming
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.0"

// https://mvnrepository.com/artifact/org.apache.bahir/spark-streaming-twitter
libraryDependencies += "org.apache.bahir" %% "spark-streaming-twitter" % "2.3.2"

// https://mvnrepository.com/artifact/org.apache.spark/spark-sql
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.0"

//// https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine
//libraryDependencies += "org.junit.jupiter" % "junit-jupiter-engine" % "5.4.1" % Test
// https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
libraryDependencies += "org.junit.jupiter" % "junit-jupiter-api" % "5.4.1" % Test

// https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter
libraryDependencies += "org.mockito" % "mockito-junit-jupiter" % "2.27.0" % Test

