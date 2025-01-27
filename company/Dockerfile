FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

RUN apk add --no-cache bash


COPY wait-for-it.sh /app/wait-for-it.sh
COPY target/*.jar /app/company-app.jar

RUN chmod +x /app/wait-for-it.sh

EXPOSE 8082

CMD ["/app/wait-for-it.sh", "employee-service:8083", "--", "java", "-jar", "company-app.jar"]
