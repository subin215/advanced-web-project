# advanced-web-project

[![Build Status](https://travis-ci.org/subin215/advanced-web-project.svg?branch=master)](https://travis-ci.org/subin215/advanced-web-project)

## Description
This project was created for the purpose of CS-389: Advanced Web and Cloud Development class being taught in Carroll College. This is a currency exchange application. The Currency Exchange API uses real-time exchange rates from Yahoo for every calculation. A signed in user can use this application to convert between four currencies. This restriction on currencies was a front-end rule assigned by the developers. The API can handle any currency exchange as long as the proper currency identifier is used.

## Collaborators
* Subin Sapkota
* Beaugh Meyer 

## Technology Used
* Java 1.8
* Play Framework
* Scala 2.11.6
* Spring 4.3.0-RELEASE
* Hibernate 4.3.10-FINAL
* MySQL
* Docker
* docker-compose
* HTML5
* CSS3
* Google Material Lite
* AWS EC2
* AWS RDS
* Travis-CI 
* Git

## Dependencies
* [Docker]( https://docs.docker.com/engine/installation/)
* [docker-compose]( https://docs.docker.com/compose/install/)

## Installation
- Clone the repository.
-  Make sure you have the dependencies installed on your workstation.
- In your terminal, build the project image with `$ sbt dist`.
- In your terminal, navigate to docker folder in project. 
- Copy the project.zip file in `../target/universal/` to the docker folder.
- Run `$ docker compose up -d` to run the application as a docker image mapped to port 7000 on your local host.
- The application uses the AWS RDS instance for database connection. 

## Future Goals
* Account for Yahoo services downtime by having a failover API to talk to for real-time exchange rate. 
* Have a truly responsive design for better accessibility on mobile devices. This would also involve closely following Google Material Design documentation for a smooth user experience. 


## API Usage
This application has a packaged REST API for currency exchange. The examples below show the usage of the API. 
- To convert from EUR to USD: `/exchange/1.00?from=EUR&to=USD`
 * Sample Result:
 ``` JSON
 {"fromCurrency":"EUR","toCurrency":"USD","fromValue":1.0,"exchangedValue":1.0614}
 ```
- Available currency identifiers:
    * Any identifier can be used as long as it is supported by Yahoo API. Please check for available currency identifiers here: [Currency Identifiers]( http://www.xe.com/iso4217.php)

## User Documentation

Opening the application leads you to the login page. First time users need to register before they 
can login to the application. 
### Registration
* Click on the "Sign up/Update account button":
![Image of registratio page](https://raw.githubusercontent.com/subin215/advanced-web-project/master/documentation/registrationStep1.png)
* Register with a username and password and click 'Sign Up'.

### Login
* Insert your registered username and password.
* Click login button after you are done to login.
![Image of login page](https://raw.githubusercontent.com/subin215/advanced-web-project/master/documentation/loginPage.png)

### Currency Exchange API
* Click on the 'Currency From' dropdown menu for list of currencies to select from.
![Image of currency from dropdown](https://raw.githubusercontent.com/subin215/advanced-web-project/master/documentation/currencyFrom1.png)
* Select currency to convert from.
![Image of currency from dropdown](https://raw.githubusercontent.com/subin215/advanced-web-project/master/documentation/currencyFrom2.png)
* Insert monetary value to convert from.
![Image of currency from inputbox](https://raw.githubusercontent.com/subin215/advanced-web-project/master/documentation/currencyFrom3.png)
* Click on the 'Currency To' dropdown menu for list of currencies to select from.
![Image of currency to dropdown](https://raw.githubusercontent.com/subin215/advanced-web-project/master/documentation/currencyFrom4.png)
* Select currency to convert to.
![Image of currency to dropdown](https://raw.githubusercontent.com/subin215/advanced-web-project/master/documentation/currencyFrom5.png)
* Click on the 'Calculate' button to calculate currency exchange result.
![Image of calculate button](https://raw.githubusercontent.com/subin215/advanced-web-project/master/documentation/currencyFrom6.png)
* Final result will look like the image below:
![Image of finished page](https://raw.githubusercontent.com/subin215/advanced-web-project/master/documentation/currencyFrom7.png)

### About Page
This page contains information on the creators and the application itself. 
* To navigate to the page, click on the 'About' button on the left navigation bar.
![Image of About navigation](https://raw.githubusercontent.com/subin215/advanced-web-project/master/documentation/aboutPage1.png)
* This is what the page looks like. To go back to currency exchange page, click on the 'Currency Exchange' navigation button.
![Image of currency exchange navigation](https://raw.githubusercontent.com/subin215/advanced-web-project/master/documentation/currencyExchangeNavigation.png)

### Logout
* To logout from the application, click on the 'Logout' button on the left navigation bar.
![Image of logout navigation](https://raw.githubusercontent.com/subin215/advanced-web-project/master/documentation/logout.png)
* This will take you back to the Login screen.


