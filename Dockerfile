# Use tomcat docker image to build container
FROM tomcat:10.1-jdk21-temurin-jammy

# Set working directory for performing copy
WORKDIR /usr/local/tomcat/webapps/

# Copy WAR file to above working directory
COPY target/*.war .

# This application listens on this port
EXPOSE 8080
