# graph-ql-app
Sample Graph QL Application using Spring boot

About Application : This is a sample spring boot application which use graphql for querying and saving data using mutation.I have used in-memory h2 dataabse for saving data, however static data is also exposed and can be used without interacting with database.
Application exposed over http endpoint and can be tested using any graphql supported tool like postman.

Graphql schema location : src/main/resources/employee.graphqls

H2 console url : http://localhost:{port}/h2-console/

Steps to build and run this application:

1) clone or download this repo 
2) build project using maven mvn clean install
3) you can directly import this project in your java ide like eclise/intellij and can run there as well

Application will run on port 9091 and port can be edit in application.properties file inside src/main/resources


GrapgQL endpoints  :

Mutation :createEmployee

Queries :
 findEmployee
 countAddress
 allEmployee
 







