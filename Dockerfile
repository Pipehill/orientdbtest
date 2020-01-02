#
# Build stage
#
FROM maven:3.5-jdk-8 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:8-jdk-alpine
COPY --from=build /home/app/target/orientdbtest-0.0.1-SNAPSHOT.jar /usr/local/lib/orientdbtest.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/orientdbtest.jar"]