# spring-docker-rabbitmq-consumer

//CREATE IMAGE
gradle build  // create jar spring-docker-rabbitmq-consumer-0.0.1-SNAPSHOT.jar
docker build -t spring-rabbitmq-consumer:v100 .  // put the jar into docker image
docker run --env=local -dp 9001:9001 spring-rabbitmq-consumer:v100
docker ps

// download rabbit mq
docker run -d --hostname my-rabbit --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management
// rabbitmq web
http://localhost:15672/
user: guest
pass: guest

//Via docker-compose
docker-compose -f docker-compose.yaml up
docker-compose -f docker-compose.yaml down
