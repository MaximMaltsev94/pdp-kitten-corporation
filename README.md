PDP 

## Execute locally with embedded mongodb

```
mvn clean install tomcat7:run
```

Embedded mongodb `start` goal is mapped to pre-integration test phase, so, in order to execute fully functioning application,
`mvn` command should be executed exactly as in code snippet above.(https://github.com/Syncleus/maven-mongodb-plugin#notes)