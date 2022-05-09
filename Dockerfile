FROM openjdk:latest
COPY target/smart-city*.jar ./app.jar
ENTRYPOINT java ${JAVA_ARGS} -jar app.jar ${APP_ARGS}
EXPOSE 8080