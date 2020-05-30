#sudo docker build -t pdp-kitten-corporation .
#sudo docker run -it --rm -p 8080:8080 pdp-kitten-corporation
#sudo docker run --name some-mongo -v /my/own/datadir:/data/db -d mongo
#sudo docker run -p 27015:27017 --name dockermongo -d mongo:4.0
#application should connect to mongo by incance id(192.168.100.4), not localhost
FROM tomcat:8.0.20-jre8

RUN rm -rf /usr/local/tomcat/webapps/ROOT

ADD target/pdp-kitten-corporation-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war