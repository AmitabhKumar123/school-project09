# Use Java 21 (matches your project)
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy jar file
COPY target/school-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Render uses dynamic port)
EXPOSE 8080

# Run application
ENTRYPOINT ["java","-jar","app.jar"]
