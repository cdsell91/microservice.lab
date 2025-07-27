FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/spreadsheet-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]