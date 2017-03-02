#!/bin/bash

docker start starterapp_mysql_container
# Create the database if it doesn't exist.
# ./mysql/init-db.sh

docker start starterapp_container

