FROM maven:latest

WORKDIR /app

COPY pom.xml /app/
COPY . /app/

RUN mvn -q -e -DskipTests package

CMD ["java", "-jar", "target/w3-assignment-1.0-SNAPSHOT.jar"]
