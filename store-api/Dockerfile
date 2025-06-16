
FROM openjdk:21-ea-17-slim-buster
WORKDIR /app
COPY target/*.jar /app/app.jar
COPY wait-for-it.sh .
RUN chmod +x wait-for-it.sh
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]