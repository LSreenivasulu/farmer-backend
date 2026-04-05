# Use Java 21 (matches your Spring Boot project)
FROM eclipse-temurin:21-jdk

# Set working directory inside the container
WORKDIR /app

# Copy all project files into the container
COPY . .

# Give execute permission to the Maven wrapper
RUN chmod +x mvnw

# Build the project (skip tests to speed up build)
RUN ./mvnw clean package -DskipTests

# Expose the port your app runs on
EXPOSE 8081

# Start the Spring Boot app, using the target jar
CMD ["sh", "-c", "java -Dserver.port=$PORT -jar target/*.jar"]