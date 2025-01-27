FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/*.jar /app/gateway-app.jar

EXPOSE 8081

CMD ["java", "-jar", "gateway-app.jar"]