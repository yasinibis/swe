# Use OpenJDK 11 JDK as the base image (includes javac)
FROM openjdk:11-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the source files
COPY websitemonitor/ /app/websitemonitor/

# Compile the Java files
RUN javac -d /app/classes /app/websitemonitor/*.java

# Set the classpath
ENV CLASSPATH=/app/classes

# Set the entrypoint
ENTRYPOINT ["java", "website.monitor.Main"] 