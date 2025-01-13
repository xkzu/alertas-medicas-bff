# Dockerfile para servicios Java
FROM maven:3.9.6-amazoncorretto-21

WORKDIR /app

# Copiar el pom.xml y los archivos fuente
COPY pom.xml .
COPY src ./src

# Empaquetar la aplicación
RUN mvn clean package -DskipTests

# Encontrar el jar generado y moverlo a un nombre conocido
RUN mv target/*.jar target/app.jar

# Exponer puerto (ajustar según el servicio)
EXPOSE 8084

# Variables de entorno por defecto para las APIs
ENV API_DOCTORS_URL=http://doctors-service:8081
ENV API_PATIENT_URL=http://patient-service:8082
ENV API_MEASUREMENT_URL=http://patient-service:8085
# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/app.jar"]