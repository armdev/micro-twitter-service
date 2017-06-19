# micro-twitter-service
Twitter search microservice

1. Edit src/main/resources/config.yml
   Fill twitter access/consumer tokens


2. mvn clean package -U
3. java -jar target/micro-twitter-service.jar server src/main/resources/config.yml
4. Access http://localhost:2017/swagger
5. Example request http://localhost:2017/v1/search/java (Search tweets with "java" kewords)
6. Just enjoy
