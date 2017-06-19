# micro-twitter-service
Twitter search microservice

1. Edit src/main/resources/config.yml
   Fill twitter access/consumer tokens

2. cd micro-twitter-service 
3. mvn clean package -U
4. java -jar target/micro-twitter-service.jar server src/main/resources/config.yml
5. Access http://localhost:2017/swagger
6. Example request http://localhost:2017/v1/search/java (Search tweets with "java" kewords)
7. Just enjoy
