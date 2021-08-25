FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
HEALTHCHECK --interval=30s --timeout=3s \
CMD curl -f http://localhost:8080/actuator/health | grep UP || exit 1