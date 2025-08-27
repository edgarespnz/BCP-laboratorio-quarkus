FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /usr/src/app
COPY . .
RUN mvn package -Dquarkus.package.type=fast-jar

FROM eclipse-temurin:21-jre-jammy
WORKDIR /work/
COPY --from=builder /usr/src/app/target/quarkus-app/ .
EXPOSE 8080
CMD ["java", "-jar", "quarkus-run.jar"]