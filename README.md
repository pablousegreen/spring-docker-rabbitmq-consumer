# spring-docker-rabbitmq-consumer
//Run with profiles
bootRun --args='--spring.profiles.active=dev'
./gradlew bootRun --args='--spring.profiles.active=dev'


//CREATE IMAGE
gradle build  // or ./gradlew clean build -> create jar spring-docker-rabbitmq-consumer-0.0.1-SNAPSHOT.jar
docker build -t spring-rabbitmq-consumer:v100 .  // put the jar into docker image
/////****RUn Without DOCKER-COMPOSE****////////
docker run --env=devdocker -dp 9002:9002 spring-rabbitmq-consumer:v100
docker ps

// download rabbit mq
docker run -d --hostname my-rabbit --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management
// rabbitmq web
http://localhost:15672/
user: guest
pass: guest

/////*****RUn With DOCKER-COMPOSE*****////////
//Via docker-compose
docker-compose -f docker-compose.yaml up
docker-compose -f docker-compose.yaml down

//Kill port
lsof -i TCP:3000 | grep LISTEN
kill -9 44133

Test:
curl --location --request POST 'localhost:9001/publish-message' \
--header 'Content-Type: application/json' \
--header 'Cookie: Cookie_1=value' \
--data-raw '{
"message": {
"body": "[{\"name\": \"Pablo\", \"dept\": \"TI\", \"salary\": \"550.00\"}, {\"name\": \"robs\", \"dept\": \"TI\", \"salary\": \"1050.00\"}]"
}
}'