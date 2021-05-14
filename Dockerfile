FROM openjdk:11
EXPOSE 8080
ADD target/ip-range-docker.jar ip-range-docker.jar
ENTRYPOINT ["java","-jar","/ip-range-docker.jar"]
