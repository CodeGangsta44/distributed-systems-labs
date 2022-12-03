FROM maven:3 as BUILD_IMAGE
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -B package

FROM openjdk:17
COPY --from=BUILD_IMAGE /workspace/target/app.jar .
ENTRYPOINT ["java","-jar","/app.jar"]
