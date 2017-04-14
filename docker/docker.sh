#!/bin/sh -x

TEMP=$(mktemp -d)

ZIP=project-1.0-SNAPSHOT.zip

FILE1="awsDockerInstance.pem"

cd docker
# MOVE REQUIRED FILES TO TEMP FOLDER
cp $ZIP $TEMP/$ZIP
cp awsDockerInstance.pem.enc $TEMP/awsDockerInstance.pem.enc
cp awsDockerInstance.pem $TEMP/awsDockerInstance.pem
chmod 700 $TEMP/awsDockerInstance.pem.enc
chmod 700 $TEMP/awsDockerInstance.pem
cp Dockerfile $TEMP/Dockerfile
cp docker-compose.yml $TEMP/docker-compose.yml
cp docker-entrypoint.sh $TEMP/docker-entrypoint.sh

cd $TEMP

# MOVE FILES TO REMOTE SERVER
sftp -i $FILE1 -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no -b /dev/stdin ubuntu@ec2-54-201-76-3.us-west-2.compute.amazonaws.com <<EOF
cd Docker
rm *
put Dockerfile
put docker-compose.yml
put docker-entrypoint.sh
put $ZIP
exit
EOF

# START DOCKER CONTAINER FOR APPLICATION ON REMOTE SERVER
ssh -i $FILE1 -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no -t -t ubuntu@ec2-54-201-76-3.us-west-2.compute.amazonaws.com <<EOF
cd Docker
chmod +x docker-entrypoint.sh
docker rm -f currencyExchange
docker rmi subin215/scala-project:1.0.1-SNAPSHOT
docker-compose up -d
exit
EOF

cd /
rm -rf $TEMP
