# For Java 8, try this
# FROM openjdk:8-jdk-alpine

FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage V2
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/jeeback.jar /usr/local/lib/demo.jar

ARG MYSQL_URL
ENV MYSQL_URL=$MYSQL_URL

ARG MYSQL_USER
ENV MYSQL_USER=$MYSQL_USER

ARG MYSQL_PASSWORD
ENV MYSQL_PASSWORD=$MYSQL_PASSWORD

ARG RABBIT_URL
ENV RABBIT_URL=$RABBIT_URL

ARG API_KEY
ENV API_KEY=$API_KEY

ARG MAILER_ADDRESS
ENV MAILER_ADDRESS=$MAILER_ADDRESS

ARG MAILER_HOST
ENV MAILER_HOST=$MAILER_HOST

ARG MAILER_PORT
ENV MAILER_PORT=$MAILER_PORT

ARG MAIL_PASSWORD
ENV MAIL_PASSWORD=$MAIL_PASSWORD

EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]
