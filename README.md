# todoList
A complete todo list backend application.
The application was made using JAVA (spring boot) and a postgres database.

# Getting start
it is simple to start the project.
1. Run the docker-file that is in "docker/" folder
2. Run the project so it will create all the tables in the postgres database 
3. Add the basic role "ROLE_ADMIN" on the role table
4. Use the application (fell free to use the postman file in "documentation/" folder to make it easier)

# Sign-Up and Sign-in
The login features are made using the spring-boot-starter-security library
To interact with the item requests, you may login beforehead, please, use the sign In and Sign Up before all
ÍMPORTANT: The login data are stored in the cookies resources, be aware of that if you are making requests on terminal (postman and browsers automatically take care of that)

# item process:
you can Create, update and delete the data (just the ones related to your account)