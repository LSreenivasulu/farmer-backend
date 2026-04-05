FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

CMD ["sh", "-c", "java -Dserver.port=$PORT -jar target/*.jar"]