# advanced-web-project

[![Build Status](https://travis-ci.org/subin215/advanced-web-project.svg?branch=master)](https://travis-ci.org/subin215/advanced-web-project)

## Description
Advanced Web Design project for CS-389-A. The project is a currency exchange application. The Currency Exchange API uses Yahoo to get exchange rates for every calculation.

## Collaborators
* Subin Sapkota
* Beaugh Meyer 

## Technology Used
* Java
* Play Framework
* Scala
* MySQL
* Docker
* docker-compose
* HTML
* CSS
* Google Material Lite
* AWS EC2
* AWS RDS
* Travis-CI
* Git

## Installation
- Create the database.
```mysql
CREATE DATABASE advWeb;
```

- Create the table.
```mysql
CREATE TABLE `User` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(40) NOT NULL DEFAULT '',
  `PASSWORD` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```
- In your terminal, build the project image with `$ sbt dist`.
- In your terminal, navigate to docker folder in project. 
- Copy the project.zip file in `../target/universal/` to the docker folder.
- Run `$ docker compose up -d` to run the application as a docker image mapped to port 7000 on your local host.



