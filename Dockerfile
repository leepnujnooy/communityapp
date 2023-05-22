FROM gradle:7.5.1-jdk17-alpine as builder
USER root
WORKDIR /build

# 그래들 파일이 변경됐을때만 새롭게 의존패키지를 다운받게함
COPY build.gradle settings.gradle /build/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

COPY . /build
RUN gradle build -x test --parallel


FROM openjdk:17.0-slim
WORKDIR /app

COPY --from=builder /bulid/build/libs/*-SNAPSHOT.jar ./app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]