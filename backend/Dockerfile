# Usa Java 21 para compilar
FROM maven:4.0.0-openjdk-21-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Imagen base para ejecutar la app
FROM openjdk:21-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Puerto por defecto de Spring Boot
EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
