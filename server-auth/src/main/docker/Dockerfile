FROM frolvlad/alpine-oraclejdk8:slim
VOLUME E:/doker-tmp
ADD server-auth-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]