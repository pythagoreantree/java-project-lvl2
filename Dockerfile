FROM gradle:7.5.0-jdk18

RUN apt-get update && apt-get install -yq make

WORKDIR /project
RUN mkdir /project/code

ENV GRADLE_USER_HOME /project/.gradle

COPY . .