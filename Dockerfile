# Stage 1: Build with Maven (Java 21)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# copy only pom.xml first
COPY StudentCrud/pom.xml .
RUN mvn dependency:go-offline -B

# then copy the source code
COPY StudentCrud/src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run with JDK
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
