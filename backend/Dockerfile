FROM maven:3.9.5-eclipse-temurin-21 as builder

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests


WORKDIR /app

FROM openjdk:21-jdk-slim

COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar","app.jar" ]