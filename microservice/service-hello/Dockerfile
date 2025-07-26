FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/service-hello-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]