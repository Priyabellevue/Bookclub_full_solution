#
## Build stage#
FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests
#
FROM openjdk:17-jdk-slim
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar
COPY --from=build /app/src/main/resources/templates /app/templates
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]