FROM openjdk:8
WORKDIR /app
COPY target/webapp-1.0-SNAPSHOT.jar webapp.jar
CMD java -jar webapp.jar