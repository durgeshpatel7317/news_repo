FROM gradle:8.0.2-jdk-focal as builder

WORKDIR /app

COPY ./gradlew /app/
COPY ./build.gradle /app/
COPY ./gradle /app/gradle

RUN ./gradlew dependencies

COPY ./src /app/src

RUN ./gradlew clean build

FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

# Name of the jar file changes as per the root directory
# Hence use the root directory name while copying the jarfile
COPY --from=builder /app/build/libs/app-0.0.1.jar /app/

ENTRYPOINT ["java", "-jar", "app-0.0.1.jar"]