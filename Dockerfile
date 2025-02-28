ARG MVN_JDK_VERSION=3.9.1-eclipse-temurin-17

FROM maven:$MVN_JDK_VERSION
LABEL description "maven ${MVN_JDK_VERSION}"

RUN apt-get -yq update 

RUN adduser -u 900 --disabled-password jenkins

RUN mkdir /workdir
RUN chown -R jenkins:jenkins /workdir
USER jenkins
