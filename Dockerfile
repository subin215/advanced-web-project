FROM java:latest

MAINTAINER subinsapkota@gmail.com

ADD /target/universal/project-1.0-SNAPSHOT.zip /

RUN unzip project-1.0-SNAPSHOT.zip \
	&& rm project-1.0-SNAPSHOT.zip

WORKDIR /project-1.0-SNAPSHOT

RUN ["chown", "-R", "daemon", "."]

USER daemon

ENTRYPOINT ["bin/project"]

CMD []

EXPOSE 9000