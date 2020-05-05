# Docker multi-stage build
 
#Stage-1: Build application with Maven
FROM maven:3-jdk-11
ADD . /workdir
WORKDIR /workdir
 
# Just echo so we can see, if everything is there :)
RUN ls -l
 
# Run Maven build
RUN mvn clean install -Ddependency-check.skip=true
RUN ls
RUN ls -l target/*
 
# 2. Just using the build artifact and then removing the build-container
FROM openjdk:11-jdk
 
MAINTAINER Y.Mahajan
VOLUME /tmp

# Add Spring Boot app.jar to Container
COPY --from=0 /workdir/target/FriendsAPIServer-0.0.0.1.jar app.jar
 
# Fire up our Spring Boot app by default
#CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
CMD [ "sh", "-c", "java -jar /app.jar" ]
