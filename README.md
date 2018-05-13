
##IP Address Validator Test

To build, test and package run the command : 
````
mvn package 
````

To start the web server use the command : 

````
java -jar target/ip-validator-0.0.1-SNAPSHOT.jar
````

The application will be available at port 8080 e.g. http:/localhost:8080/validator

To use the service using [curl](https://curl.haxx.se/) use the command :
````
curl -d '["123.12.255.1", "123.456.789.012", "hello.there.mr.smith", "11.22.33.44.55", "0.2.3.4"]' -H "Content-Type: application/json" -X POST http://localhost:8080/validator
````
To use the service using [httpie](https://httpie.org/) use the command :
````
echo '["123.12.255.1", "123.456.789.012", "hello.there.mr.smith", "11.22.33.44.55", "0.2.3.4"]' | http POST http://localhost:8080/validator
````
###Assumptions
- The JSON passed to the service must be a valid JSON **array**.
- An empty input array is valid, and returns an empty array.
