FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
EXPOSE 10000
ENTRYPOINT ["java", "-Dspring.config.location=classpath:/application.properties,/etc/secrets/application-prod.properties", "-jar", "target/farmer-0.0.1-SNAPSHOT.jar"]