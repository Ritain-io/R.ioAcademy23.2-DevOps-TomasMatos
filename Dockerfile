# Use a base image with Java installed
FROM openjdk:17-jdk-alpine

 

# Set the working directory in the container
WORKDIR /app

 

# Copy the JAR file built by Maven into the container
COPY Math/target/math-0.0.1-SNAPSHOT.jar /app/math.jar

 

# Expose the port that your Spring Boot application listens on (assuming it's 8080)
EXPOSE 8080

 

# Define the command to run your application
CMD ["java", "-jar", "math.jar"]


# Set environment variables if needed 
ENV APP_VERSION=latest
