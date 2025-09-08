# Stage 1: Build with Maven
FROM maven:3.8.5-openjdk-21-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run with JDK
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/StudentCrud-0.0.1-SNAPSHOT.jar StudentCrud.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "StudentCrud.jar"]
