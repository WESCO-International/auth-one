FROM ubuntu


ARG DEBIAN_FRONTEND=noninteractive

ARG user=user

RUN adduser $user

RUN apt-get update


# INSTALL MAVEN
ARG MAVEN_VERSION=3.8.5
ARG USER_HOME_DIR="/root"
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN apt-get install -y curl gnupg2 wget ca-certificates

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
 && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
 && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
 && rm -f /tmp/apache-maven.tar.gz \
 && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

RUN apt install -y software-properties-common

# Install Java.
RUN apt install openjdk-11-jdk -y

RUN mkdir -p /tmp

RUN chown -R ${user}:${user} /tmp 

RUN chmod 777 /tmp

RUN mkdir -p /data 

RUN chown -R ${user}:${user} /data

RUN chmod -R 777 /data

COPY . /app

COPY ./target /app/target

WORKDIR /app

USER ${user}

VOLUME [ "/data" ]
