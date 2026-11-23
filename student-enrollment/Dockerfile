FROM maven:3.9-eclipse-temurin-21 AS build
ARG SERVICE
WORKDIR /src
COPY . .
RUN mvn -B -pl "${SERVICE}" -am package && cp "${SERVICE}/target/${SERVICE}.jar" /app.jar

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app.jar app.jar
USER 10001:10001
ENTRYPOINT ["java", "-jar", "app.jar"]
