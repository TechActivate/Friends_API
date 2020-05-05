#Stage-1: Build application with Maven
FROM maven:3-jdk-11 as BUILD_STAGE
ADD . /workdir
WORKDIR /workdir
 
# Just echo so we can see, if everything is there :)
RUN ls -l
 
# Run Maven build
RUN mvn clean install -Ddependency-check.skip=true
RUN ls
RUN ls -l target/*

#Stage-2: Add build artifcat to the container
FROM openjdk:11-jdk
 
MAINTAINER Y.Mahajan

# Add Spring Boot app.jar to Container
COPY --from=BUILD_STAGE /workdir/target/FriendsAPIServer-0.0.0.1.jar app.jar
 
# Fire up our Spring Boot app by default
CMD [ "sh", "-c", "java -jar /app.jar" ]
