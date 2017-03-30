name := """Project"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  "org.hibernate" % "hibernate-entitymanager" % "4.3.10.Final",
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.mindrot"  % "jbcrypt"   % "0.3m",
  "javax.inject" % "javax.inject" % "1",
  "org.springframework" % "spring-aop"             % "4.3.0.RELEASE",
  "org.springframework" % "spring-beans"           % "4.3.0.RELEASE",
  "org.springframework" % "spring-context"         % "4.3.0.RELEASE",
  "org.springframework" % "spring-core"            % "4.3.0.RELEASE",
  "org.springframework" % "spring-jdbc"            % "4.3.0.RELEASE",
  "org.springframework" % "spring-orm"             % "4.3.0.RELEASE",
  "org.springframework" % "spring-tx"              % "4.3.0.RELEASE",
  "org.springframework" % "spring-test"            % "4.3.0.RELEASE",
  "org.apache.httpcomponents" % "httpclient"       % "4.5.3",
  "junit" % "junit" % "4.12" % "test",
  cache,
  javaWs
)

libraryDependencies += "org.webjars" % "jquery" % "1.11.2"