#
#
#
FROM eclipse-temurin:17-jdk-jammy
VOLUME /tmp
COPY target/*.jar bookclub.jar
ENTRYPOINT ["java", "-jar", "/bookclub.jar"]
EXPOSE 8080