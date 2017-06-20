FROM openjdk:8-alpine

VOLUME /tmp
ADD app.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java ${JAVA_OPTS} -Dspring.profiles.active=${PROFILE} -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]