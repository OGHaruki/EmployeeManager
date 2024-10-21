FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/*.jar /app/employee-app.jar

EXPOSE 8083

CMD ["java", "-jar", "employee-app.jar"]
