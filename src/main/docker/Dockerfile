FROM openjdk:16-alpine
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} shortened-url-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "shortened-url-0.0.1-SNAPSHOT.jar"]