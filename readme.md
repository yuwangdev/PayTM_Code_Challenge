## Readme: PayTM job code challenge solution 

### How to run:
- under the directory of the project, use Gradle to build the project. 
- currently the project is using localhost:5000, but can be changed in the application.properties file. 
- go to the build/libs/ run $java -jar paytm-0.0.1-SNAPSHOT.jar
- use the localhost:5000/h2-console to use as the H2 client.

### Endpoint
- /hello: GET, return helloworld
- /search/{username}/{key}: GET, search via the LCBO Product API. 
- /random/{username}: GET, randomly search, with an unused search keyword. 
- /user/login: POST, user login
- /user/create: POST: user sign up 
- /user/update: POST, update password and check old password
- /user: GET, return all user info 
- /user/count: return user count 
- /user/delete/{username}: delete an user
- /activity/{username}: GET, get all activities of an user 

### Challenge 1 

I use Java 8 and SpringBoot 1.4.6 for this project. in brief, the technologies involved are as follows.

- SpringBoot 1.4.6 as the web service and dependency injection framework. 
- SpringBoot built-in Tomcat server as the container. 
- SpringBoot built-in H2 database as the relational database persistence medium. 
- Spring JPA as the object relational mapping framework.
- I also use Lombok, Guava, and Gson as dependency library.
- I used Gradle as the build tool, and git as version control.  

The reason I especially used Java SpringBoot for a potentially high concurrency application includes,

- Java/Scala is based on JVM, and its application can be easily deployed on Docker container as the micro-service provider. At the meantime, Zookeeper can be used for managing these instances, to provide real-time scalability and great flexibility. 

- As a payment related service provider, relational database is always the only one choice for storing financial information to guarantee the data quality and safety. Meanwhile, load balancing, caching, and master/slaver hierarchy ensure the performance and data safety. 

- Potential technologies, e.g. Spark stream, Kafka message queue, may also increase the system performance when the system deals with high concurrency application use case. 

### Challenge 2
- Implemented by the hello() method in the RestService class, using the LCBO api.

### Challenge 3
- Implemented by the search() method in the RestService class. Use RestTempalte for Restful API Get implementation.

### Challenge 4
- Implemented by the UserDao and UserActivityDao, and several related restful controller methods. 

### Challenge 5
- Implemented in the UserActivityDao, which keeps track of the user search history. 

### Challenge 6:
- test by JUnit test, and Spring unit test Mock framework. 

### Challenge 7
- sent via email. 

### Challenge 8
- in the current project, I use UserActivityDao to keep track of the search history of specific user, and generate random string for the random search. 

- Suggestion: use caching system (e.g. Redis). WHne an user randomly searches the first time, the database will be hit. Then, in a relatively short period, the entire historical records would be stored in the in-memory cache. When time exceeds the threshold, the records will be purged.  




