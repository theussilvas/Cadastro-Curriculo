FROM maven:3.9.5-eclipse-temurin-17 as builder

WORKDIR /app

COPY . .

RUN mvn clean package -DskiTests


WORKDIR /app

FROM openjdk:17-jdk-slim

COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar","app.jar" ]