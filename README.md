# calc-comm-commander #

This README would normally document whatever steps are necessary to get your application up and running.

### Requirements ###

- **Maven Version :** Apache Maven 3.6.1 and above
- **JDK Version :** OpenJDK 17.0.2
- **SpringBoot Version :** 3.2.3

### Local environment PostgerSQL database setup steps:
- Step 1: Login:
sudo -u postgres psql
- Step 2: Create a database:
create database calcominv;
- Step 3: Create a user:
create user calcUser with password 'cal@1234';
- Step 4: Grant privileges to created user:
grant all privileges on database calcominv to calcUser;

### Swagger documentation available at:
```  
http://localhost:9017/swagger-ui/index.html
```  