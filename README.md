# Spring React Starter App
This is a simple starter web app using Spring Boot and React.js, also wired to use database to persist state.
This project provides isomorphic web applicaion, i.e. using the same templates in server and client side.


# Requirements
* Maven 3 and above
* Java 8
* MySQL


## Quickstart
1. Install and set up MySQL. You may use the scripts provided in 'mysql' directory to do so.
1. Add an entry in your '/etc/hosts' file for 'mysql', pointing to your local machine (127.0.0.1).
1. Check src/main/resources/application.properties and make sure you have the right database configuration.
1. Run 'mvn clean install'.
1. Run ./run-local-server.sh script.
1. Access the application at http://localhost:8080.


## Running in Docker
1. Make sure you can at least build the project, runnign 'mvn clean install'.
1. Run ./docker-build-images.sh to create Docker images.
1. Run ./docker-build-containers.sh to create Docker containers.
1. Run ./docker-start-containers.sh to start containers.
1. Access the application at http://localhost:8080.
1. Stop containers by running './docker-stop-containers.sh'.


## Credits and Links
* [Benjamin Winterberg's blog post](http://winterbe.com/posts/2015/02/16/isomorphic-react-webapps-on-the-jvm/) on how to build isomorphic webapps on JVM using React.js and Spring Boot.
* [React Tutorial](https://facebook.github.io/react/docs/tutorial.html)
* [Spring Boot - Working with SQL databases](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html)

