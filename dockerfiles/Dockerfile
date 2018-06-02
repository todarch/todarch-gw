FROM openjdk:8-jdk-alpine

LABEL maintainer="selimssevgi@gmail.com"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/gw-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} gw.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gw.jar"]
