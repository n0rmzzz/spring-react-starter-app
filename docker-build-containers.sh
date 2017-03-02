#!/bin/bash

# Run ./docker-build-images.sh first.

# Create DB container
docker-compose -f docker-compose-dev.yml up -d starterapp_mysql
# Allow it some time to start
# Ideally we would probe the service to see if it has become available
echo "Waiting for MySQL container to start up..."
sleep 10

# Connect to MySQL docker container and initialize the database.
./mysql/init-db.sh "mysqlrootpass"

# Create the app container
docker-compose -f docker-compose-dev.yml up -d starterapp

