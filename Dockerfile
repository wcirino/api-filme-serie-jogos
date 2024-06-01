FROM adoptopenjdk/openjdk17:alpine
WORKDIR /app
COPY target/api-filme-series-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8056
CMD ["java", "-jar", "app.jar"]

