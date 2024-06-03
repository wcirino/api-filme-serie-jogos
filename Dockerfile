FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/api-filme-series-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8089
CMD ["java", "-jar", "app.jar"]
