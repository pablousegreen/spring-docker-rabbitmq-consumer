FROM openjdk:11

EXPOSE 9001

ADD build/libs/spring-docker-rabbitmq-consumer-0.0.1-SNAPSHOT.jar spring-docker-rabbitmq-consumer-0.0.1-SNAPSHOT.jar

#ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${ENV} -Djava.security.egd=file:/dev/./urandom", "spring-docker-rabbitmq-producer-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "spring-docker-rabbitmq-consumer-0.0.1-SNAPSHOT.jar" ]

#CMD java -Dspring.profiles.active=prod