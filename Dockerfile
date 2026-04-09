# ---------- STEP 1: BUILD ----------
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy all project files
COPY . .

# Build jar
RUN mvn clean package -DskipTests

# ---------- STEP 2: RUN ----------
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Dynamic port
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
