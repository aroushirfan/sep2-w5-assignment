FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app
COPY . /app

RUN mvn -B clean package -DskipTests


FROM eclipse-temurin:21-jdk

# Install only what JavaFX needs
RUN apt-get update && apt-get install -y \
    libgtk-3-0 \
    libgl1-mesa-glx \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy jar + dependencies
COPY --from=build /app/target/w3-assignment-1.0-SNAPSHOT.jar app.jar
COPY --from=build /app/target/lib ./lib

ENV DISPLAY=host.docker.internal:0.0

CMD ["java", "-cp", "app.jar:lib/*", "app.Main"]
