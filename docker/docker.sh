#!/bin/sh -x

TEMP=$(mktemp -d)

ZIP=project-1.0-SNAPSHOT.zip

if [[ "$USER"!="subin" ]];
then
FILE1="testDockerInstance.pem"
cd docker
cp testDockerInstance.pem $TEMP
cd ..
else
FILE1="~/.ssh/testDockerInstance.pem"
# Package application
cd ..
sbt clean dist
cd docker
fi

cp ../target/universal/$ZIP $TEMP
cp Dockerfile $TEMP
cp docker-compose.yml $TEMP
cp docker-entrypoint.sh $TEMP

cd $TEMP

sftp -i "~/.ssh/testDockerInstance.pem" -o StringHostKeyChecking=no -b /dev/stdin ubuntu@52.41.69.145 <<EOF
cd Docker
rm *
put Dockerfile
put docker-compose.yml
put docker-entrypoint.sh
put $ZIP
exit
EOF

ssh -i "~/.ssh/testDockerInstance.pem" -o StringHostKeyChecking=no -t -t ubuntu@52.41.69.145 <<EOF
cd Docker
chmod 700 docker-entrypoint.sh
docker rmi subin215/scala-project:1.0.1-SNAPSHOT
docker-compose up -d
exit
EOF

cd /
rm -rf $TEMP
