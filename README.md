# INFO-H303 Projet de base de donnée

***

## Getting started

1. You must have installed mysql
2. You must create and grant privileges to a new user

`create user 'bd_user'@'localhost' identified by 'bd_password';`

`grant all privileges on * . * to 'bd_user'@'localhost';`

`flush privileges;`

3. Execute the given .jar (openjdk 15)