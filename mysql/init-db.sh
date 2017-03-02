# init db

ROOT_PASSWORD=$1
MYSQL_CMD="mysql --protocol tcp -h mysql -P 3306"

echo | $MYSQL_CMD -u root -p$ROOT_PASSWORD << END_OF_SCRIPT
CREATE DATABASE commentbox;
GRANT ALL PRIVILEGES ON commentbox.* TO 'comments'@'%' IDENTIFIED BY 'comments';
END_OF_SCRIPT

echo 'show tables;' | $MYSQL_CMD -u comments -pcomments commentbox

