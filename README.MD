### `mvn quarkus:dev`
```
Listening for transport dt_socket at address: 5005
2019-10-08 09:27:36,514 INFO  [io.qua.dep.QuarkusAugmentor] (main) Beginning quarkus augmentation
2019-10-08 09:27:37,516 INFO  [io.qua.dep.QuarkusAugmentor] (main) Quarkus augmentation completed in 1002ms
2019-10-08 09:27:37,883 INFO  [io.quarkus] (main) Quarkus 0.23.2 started in 1.471s. Listening on: http://0.0.0.0:8080
2019-10-08 09:27:37,886 INFO  [io.quarkus] (main) Profile dev activated. Live Coding activated.
2019-10-08 09:27:37,886 INFO  [io.quarkus] (main) Installed features: [cdi, resteasy]
```

* Welcome page [http://localhost:8080](http://localhost:8080)
* HelloResource [http://localhost:8080/hello](http://localhost:8080/hello)
* **`INFO  [io.quarkus] (main) Profile dev activated. Live Coding activated.`**
    ```
    └── target
        ├── jugin-quarkus-1.0.0-SNAPSHOT.jar
        ├── jugin-quarkus-1.0.0-SNAPSHOT-runner.jar
        ├── lib
        │   ├── com.fasterxml.jackson.core.jackson-annotations-2.9.10.jar
        │   ├── com.fasterxml.jackson.core.jackson-core-2.9.10.jar
        │   ├── com.fasterxml.jackson.core.jackson-databind-2.9.10.jar
        │   ├── com.sun.activation.jakarta.activation-1.2.1.jar
        │   ├── io.netty.netty-buffer-4.1.39.Final.jar
        │   ├── io.netty.netty-resolver-4.1.39.Final.jar
        │   ├── io.netty.netty-resolver-dns-4.1.39.Final.jar
        │   ├── io.quarkus.arc.arc-0.23.2.jar
        │   ├── io.quarkus.http.quarkus-http-core-3.0.0.Alpha7.jar
        │   ├── io.quarkus.http.quarkus-http-http-core-3.0.0.Alpha7.jar
        │   ├── io.quarkus.http.quarkus-http-servlet-3.0.0.Alpha7.jar
  ```

### `mvn quarkus:list-extensions`
```
Current Quarkus extensions available: 
Apache Kafka Client                                quarkus-kafka-client                              
Apache Kafka Streams                               quarkus-kafka-streams                             
Flyway                                             quarkus-flyway                                    
Hibernate ORM                                      quarkus-hibernate-orm                             
Hibernate Validator                                quarkus-hibernate-validator                       
JDBC Driver - H2                                   quarkus-jdbc-h2                                   
JDBC Driver - PostgreSQL                           quarkus-jdbc-postgresql                           
Jackson                                            quarkus-jackson                                   
JSON-B                                             quarkus-jsonb                                     
Kotlin                                             quarkus-kotlin                                    
Kubernetes Client                                  quarkus-kubernetes-client                         
MongoDB Client                                     quarkus-mongodb-client                            
Narayana JTA - Transaction manager                 quarkus-narayana-jta                              
RESTEasy - Jackson                                 quarkus-resteasy-jackson                          
Scala                                              quarkus-scala                                     
Scheduler                                          quarkus-scheduler                                 
Security OAuth2                                    quarkus-elytron-security-oauth2                   
SmallRye Health                                    quarkus-smallrye-health                           
SmallRye JWT                                       quarkus-smallrye-jwt                              
REST Client                                        quarkus-rest-client                               
Swagger UI                                         quarkus-swagger-ui                                

To get more information, append -Dquarkus.extension.format=full to your command line.

Add an extension to your project by adding the dependency to your project or use `mvn quarkus:add-extension -Dextensions="artifactId"
```

### `mvn quarkus:add-extension -Dextensions="hibernate-validator,quarkus-resteasy-jackson"`
```
Adding extension io.quarkus:quarkus-hibernate-validator
Adding extension io.quarkus:quarkus-resteasy-jackson
```

### `mvn install -Pnative`
