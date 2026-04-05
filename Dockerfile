# Use Java 21
FROM eclipse-temurin:21-jdk

# Set working directory inside the container
WORKDIR /app

# Copy all project files
COPY . .

# Give execute permission to Maven wrapper
RUN chmod +x mvnw

# Build the project and skip tests
RUN ./mvnw clean package -DskipTests

# Expose port 8081
EXPOSE 8081

# Run the Spring Boot app
CMD ["sh", "-c", "java -Dserver.port=8081 -jar target/*.jar"]