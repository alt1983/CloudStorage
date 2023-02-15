FROM openjdk:17-alpine

EXPOSE 8080

ADD out/artifacts/CloudStorage_jar/CloudStorage.jar myapp.jar

ENTRYPOINT ["java","-jar","/myapp.jar"]