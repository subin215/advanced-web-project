#!/bin/sh -x

TEMP=$(mktemp -d)

ZIP=project-1.0-SNAPSHOT.zip

FILE1="~/.ssh/ubuntuInstance.pem"

# PACKAGE APPLICATION
cd ..
sbt clean dist
cd docker

# MOVE REQUIRED FILES TO TEMP FOLDER
cp ../target/universal/$ZIP $TEMP
cp Dockerfile $TEMP
cp docker-compose.yml $TEMP
cp docker-entrypoint.sh $TEMP

cd $TEMP

# MOVE FILES TO REMOTE SERVER
sftp -i $FILE1 -b /dev/stdin ubuntu@54.218.59.214 <<EOF
cd Docker
rm *
put Dockerfile
put docker-compose.yml
put docker-entrypoint.sh
put $ZIP
exit
EOF

# START DOCKER CONTAINER FOR APPLICATION ON REMOTE SERVER
ssh -i $FILE1 -t -t ubuntu@54.218.59.214 <<EOF
cd Docker
chmod 700 docker-entrypoint.sh
docker rmi subin215/scala-project:1.0.1-SNAPSHOT
docker-compose up -d
exit
EOF

cd /
rm -rf $TEMP
