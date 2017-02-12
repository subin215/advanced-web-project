name := """Project"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  "org.hibernate" % "hibernate-entitymanager" % "4.3.10.Final",
  "mysql" % "mysql-connector-java" % "5.1.18",
  cache,
  javaWs
)

libraryDependencies += "org.webjars" % "jquery" % "1.11.2"
