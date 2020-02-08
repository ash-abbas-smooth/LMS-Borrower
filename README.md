# Borrower Microservice for LMS
A microservice for the Library Management system which allows the borrower users to check out the different library branches, check out a book and return a book.

## Getting Started
These instructions will get you a copy of the project in your local machine for development and testing purposes.

### Prerequisites
The tools you need:

```
Maven: Set up in your environment and it used to package the Application to a jar file.
JDK (8+): To run the java application.
```

### Installing

1. Extract the files from the Repository to a directory of your choice.

2. Go to the directory of the file. Go into lms-borrower directory and run the command below. This should create directory called 'target' and create a .jar application called borrowermsvc-0.0.1-SNAPSHOT.jar.
```
mvn clean package
```

3. To run the jar application, simply use the command:
```
java -jar target/borrowermsvc-0.0.1-SNAPSHOT.jar
```

4. This should run the application under your localhost with the port 8083. To check if the application is working, simply go check the url:

```
http://localhost:8083/lms/borrower/branches
```
This should list out the available branches within the in-memory database.