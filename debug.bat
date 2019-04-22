echo %MAVEN_OPTS%
set MAVEN_OPTS=-XX:+CMSClassUnloadingEnabled -Dcom.sun.management.jmxremote -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5566
echo %MAVEN_OPTS%

call mvn clean install tomcat7:run