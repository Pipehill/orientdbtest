#
# Build stage
#
FROM maven:3.5-jdk-8 AS build
WORKDIR /usr/src/app
COPY ./pom.xml .
RUN mvn dependency:go-offline
COPY ./ .
RUN mvn package

#
# Package stage
#
FROM openjdk:8-jdk-alpine
COPY --from=build /usr/src/app/target/orientdbtest-0.0.1-SNAPSHOT.jar /usr/local/lib/orientdbtest.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/local/lib/orientdbtest.jar"]
