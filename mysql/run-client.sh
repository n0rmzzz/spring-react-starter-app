#!/bin/sh

# This will work for both local and dockerized MySQL
mysql --protocol tcp -h localhost -u comments -pcomments commentbox

