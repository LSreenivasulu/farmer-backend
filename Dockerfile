FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
EXPOSE 10000
ENTRYPOINT ["java", \
  "-Dspring.datasource.url=${SPRING_DATASOURCE_URL}", \
  "-Dspring.datasource.username=${SPRING_DATASOURCE_USERNAME}", \
  "-Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD}", \
  "-Dspring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver", \
  "-Dspring.jpa.hibernate.ddl-auto=update", \
  "-Dserver.port=10000", \
  "-jar", "target/farmer-0.0.1-SNAPSHOT.jar"]