# Use an official Maven image to build the app
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install

# Use a smaller JDK image to run the app
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Set environment variables for the database
ENV DB_HOST=dpg-crdrk3rgbbvc73d5st00-a.virginia-postgres.render.com
ENV DB_PORT=5432
ENV DB_NAME=programmedb
ENV DB_USER=programmedb_user
ENV DB_PASSWORD=NXIG9jKKDLOUC3q0yblisrJXhBvxGxRx

# Expose the port that Spring Boot runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
