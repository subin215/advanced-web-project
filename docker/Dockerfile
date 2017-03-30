FROM anapsix/alpine-java:8_server-jre

MAINTAINER subinsapkota@gmail.com

ENV ZIP=project-1.0-SNAPSHOT.zip

ADD $ZIP /
COPY docker-entrypoint.sh /

RUN unzip $ZIP \
	&& rm $ZIP \
	&& dos2unix /docker-entrypoint.sh

WORKDIR /project-1.0-SNAPSHOT

ENTRYPOINT ["/docker-entrypoint.sh"]

CMD ["/bin/sh", "-c", "bin/project"]

EXPOSE 9000
