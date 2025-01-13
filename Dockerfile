# Build stage
FROM maven:3.9.6-amazoncorretto-21 AS builder
WORKDIR /app

# Cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Build app
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM amazoncorretto:21-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Configuración de la aplicación
EXPOSE 8084
ENV API_DOCTORS_URL=http://doctor:8081 \
    API_PATIENT_URL=http://patient:8082 \
    API_MEASUREMENT_URL=http://measurement:8085

ENTRYPOINT ["java", "-jar", "app.jar"]