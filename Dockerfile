# openjdk:17-oracle
FROM eclipse-temurin:17.0.6_10-jdk-alpine
WORKDIR /opt
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
