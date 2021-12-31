# spring-docker-rabbitmq-consumer
//Run with profiles
bootRun --args='--spring.profiles.active=dev'
./gradlew bootRun --args='--spring.profiles.active=dev'

/************FOR TEST LOCAL*************/
// Run RabbitMQ:
docker run -d --hostname my-rabbit --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management

// Run Producer:
docker run -dp:9001:9001 spring-rabbitmq:v100

//Run with profiles
bootRun --args='--spring.profiles.active=dev'
./gradlew bootRun --args='--spring.profiles.active=dev'

Or
-> Comment spring-rabbitmq-consumer:v100 image in docker-compose.yaml
docker-compose -f docker-compose.yaml up

/************FOR DEPLOY IN CLOUD*************/

//CREATE IMAGE
gradle build  // or ./gradlew clean build -> create jar spring-docker-rabbitmq-consumer-0.0.1-SNAPSHOT.jar
docker build -t spring-rabbitmq-consumer:v100 .  // put the jar into docker image
/////****RUn only for i-container Without DOCKER-COMPOSE****////////
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