version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.8"
// External Confluent Repo
resolvers += "Confluent" at "https://packages.confluent.io/maven/"
// Logging
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.36"
// Kafka Streams library
libraryDependencies += "org.apache.kafka" % "kafka-streams" % "3.1.0"
// Kafka Avro library
libraryDependencies += "org.apache.avro" % "avro" % "1.11.0"
// SerDe Class for Avro records
libraryDependencies += "io.confluent" % "kafka-streams-avro-serde" % "7.1.1"