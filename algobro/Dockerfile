FROM maven:3.9.9-eclipse-temurin-22-jammy AS build
WORKDIR /build
COPY . .
RUN mvn package shade:shade

FROM eclipse-temurin:22-jdk
ENV LOG4J_CONFIGURATION_FILE="/app/log4j2.xml"

WORKDIR /app
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v2.7.0/opentelemetry-javaagent.jar \
  ./opentelemetry-javaagent.jar

COPY log4j2.xml ./log4j2.xml
COPY --from=build /build/target/algobro-*.jar ./app.jar

ENTRYPOINT ["java", "-javaagent:./opentelemetry-javaagent.jar", "-jar", "./app.jar"]
