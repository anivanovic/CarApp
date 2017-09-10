# Car app

Sample application done with Spring Boot, Hibernate and H2 database.

## Installation

Clone repository and navigate to project directory in terminal. Execute
command.
```
gradlew eclipse build
```

After command executes import project to Eclipse and run Application.java
file or execute command in terminal
```
gradlew bootRun
```

Application should be running on address http://localhost:8080/carapp.

## Examples of rest calls

POST /carowner/create

body:
```
{
	"firstName": "Marko",
	"lastName": "Perković",
	"birthday": "11.05.1983"
}
```

GET /carowner/10

POST /carmodel/create

body:
```
{
	"manufacturer": "BMW",
	"model": "M2",
	"horsepower": 426,
	"modelYear": 2012,
	"transmissionType": "automatic",
	"fuelType": "petrole"
}
```

POST /car/create

body:
```
{
	"color": "red",
	"price": 45800.80,
	"vehcileIdentificationNumber": "JP0213TTD",
	"carModel": {
		"id": 20
	}
}
```

POST /carowner/addCar

body:
```
{
	"carId": 30,
	"carOwnerId": 10
}
```
