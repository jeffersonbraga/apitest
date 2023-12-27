FROM openjdk:20-bullseye
LABEL authors="jefferson"

COPY target/apitest-0.0.1-SNAPSHOT.jar apitest.jar
ENTRYPOINT ["java","-jar","/apitest.jar"]
