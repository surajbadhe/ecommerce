# --- Stage 1: Build Stage ---
# Use a Maven image with Java 21 to build the application
# FIX: Added "AS builder" to name this stage.
FROM maven:3.9-eclipse-temurin-21 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file first to leverage Docker's layer caching.
# This way, dependencies are only re-downloaded if pom.xml changes.
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the application, skipping tests for a faster build
RUN mvn clean install -DskipTests


# --- Stage 2: Runtime Stage ---
# Use a lightweight JRE image for the final container
FROM eclipse-temurin:21-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the executable JAR file from the build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port the application runs on (default is 8080)
EXPOSE 8080

# The command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
