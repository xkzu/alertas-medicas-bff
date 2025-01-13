# Dockerfile para servicios Java
FROM maven:3.9.6-amazoncorretto-21

WORKDIR /app

# Copiar el pom.xml y los archivos fuente
COPY pom.xml .
COPY src ./src

# Copiar el wallet
COPY wallet /wallet

# Empaquetar la aplicación
RUN mvn clean package -DskipTests

# Encontrar el jar generado y moverlo a un nombre conocido
RUN mv target/*.jar target/app.jar

# Exponer puerto (ajustar según el servicio)
EXPOSE 8084

# Establecer la variable de entorno para el wallet
ENV TNS_ADMIN=/wallet

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/app.jar"]