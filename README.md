# advanced-web-project
## Description
Advanced Web Design project for CS-389-A. _*Insert Description here*_
## Collaborators
* Subin Sapkota
* Beaugh Meyer 

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

- Configure the proper user and password for database in application.conf file.
