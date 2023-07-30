FROM adoptopenjdk/openjdk11:jdk-11.0.20_8-ubuntu-slim

WORKDIR /app 

COPY . .

RUN ./mvnw install

ENTRYPOINT [ "./mvnw", "spring-boot:run" ]