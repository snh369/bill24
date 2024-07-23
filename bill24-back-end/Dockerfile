FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY build/libs/0.0.1-SNAPSHOT.jar bill24_api.jar
ENTRYPOINT ["java", "-jar", "bill24_api.jar"]