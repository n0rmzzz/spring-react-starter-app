#!/bin/bash

# Re-build the base image
./docker-build-base-image.sh

# Remove existing containers
docker rm -f starterapp_mysql_container
docker rm -f starterapp_container

mkdir -p ./docker/app-data/maven-repository
mvn clean install -DskipTests -Dmaven.repo.local=./docker/app-data/maven-repository

# Build images
docker-compose -f docker-compose-dev.yml build

