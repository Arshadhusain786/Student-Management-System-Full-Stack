# Stage 1: Build with Maven (Java 21 support)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run with JDK
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/StudentCrud-0.0.1-SNAPSHOT.jar StudentCrud.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "StudentCrud.jar"]
