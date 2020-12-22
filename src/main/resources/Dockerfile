FROM frolvlad/alpine-java:jdk8-slim
RUN apk add -U tzdata
RUN cp /usr/share/zoneinfo/America/Santiago /etc/localtime
RUN date
VOLUME /opt/proyectos/nfsshared/spring-boot-web-app
RUN mkdir -p /spring-boot-web-app/lib/
RUN mkdir -p /spring-boot-web-app/config/
RUN mkdir -p /spring-boot-web-app/logs/
ADD arqutipo-web.jar /spring-boot-web-app/lib/app.jar
ADD bootstrap.yml /spring-boot-web-app/config
ADD logback.xml /spring-boot-web-app/config
ADD application-arqutipo-web-messages.properties /spring-boot-web-app/config
WORKDIR /spring-boot-web-app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","lib/app.jar"]
EXPOSE 9000
