FROM openjdk:17
LABEL authors="nrsmsf"
COPY build/libs/datalore-tests-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]