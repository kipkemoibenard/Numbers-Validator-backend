FROM openjdk:14

# Set the working directory in the container to /app
WORKDIR /app

# Copy the build artifact from the host to the container
COPY target/phone-number-validator-0.0.1-SNAPSHOT.jar .

# Expose port 8080
EXPOSE 8080

# Set the startup command to run the jar file
CMD ["java", "-jar", "phone-number-validator-0.0.1-SNAPSHOT.jar"]
