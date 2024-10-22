# Bookwarm Backend

Backend microservice application for book tracking application

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Packaging the Project](#packaging-the-project)
- [Running the Application](#running-the-application)

## Prerequisites

Before you begin, ensure you have the following:

- A Linux instance (Ubuntu, Fedora, etc.)
- Java Development Kit (JDK) installed
- Basic knowledge of using the terminal

## Installation

### Step 1: Install Java

Maven requires Java. You can install OpenJDK using the following commands:

For Ubuntu/Debian:
```bash
sudo apt update
sudo apt install openjdk-11-jdk
```

Or if it is already installed, check if there are already jdk's on directory /usr/java/
```bash
ls \usr\java\
```

If there are multiple Java installations in your Linux instance. You need to set the JAVA_HOME environment variable to jdk-11 or above
```bash
export JAVA_HOME=/usr/java/jdk-11.X.X
```

### Step 2: Install Maven
Run the command
```bash
sudo yum install -y maven
```

Check whether maven is installed with the command mvn. The command output should be as shown in the screenshot below
```bash
mvn
```

## Packaging the Project

### Step 1: Navigate to the project directory
```bash
cd /exampleLocation/Bookwarm
```

### Step 2: Package the application: Run the following command to package your Maven project:
```bash
mvn clean package
```

This command will:
* Clean the target directory
* Compile the code
* Run tests
* Package the application into a JAR file (or WAR file, depending on your project configuration)

### Step 3: Find the packaged file
After a successful build, the packaged file will be located in the target
```bash
ls target/
```

## Running the Application

### To run your packaged application, use the following command:
```bash
java -jar target/Bookwarm 0.0.1-SNAPSHOT .jar
```



