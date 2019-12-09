FROM openjdk:8u111-jdk-alpine
ADD /build/libs/kafka-example-listener-0.0.1.jar app.jar
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Duser.timezone=Europe/Moscow -jar /app.jar