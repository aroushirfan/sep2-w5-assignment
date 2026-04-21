FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app
COPY . /app

RUN mvn -B clean package -DskipTests


FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    openjfx \
    libgtk-3-0 \
    libgl1-mesa-glx \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY --from=build /app/target/w3-assignment-1.0-SNAPSHOT.jar app.jar

ENV DISPLAY=host.docker.internal:0.0

CMD ["java",
     "--module-path", "/usr/share/openjfx/lib",
     "--add-modules", "javafx.controls,javafx.fxml",
     "-jar", "app.jar"]
