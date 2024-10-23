# Bookwarm Backend

This project is a microservice-based backend application designed to run in a Docker container within a Kubernetes cluster. 
It connects to an Oracle Autonomous Database to manage user data efficiently. The application exposes RESTful APIs that handle various HTTP requests, including GET, PUT, and DELETE, allowing users to manipulate user Books records seamlessly.

### Key Features
- **Microservices Architecture**: Built using a microservice architecture for scalability and maintainability.
- **Docker & Kubernetes**: Containerized for easy deployment and orchestration within a Kubernetes cluster.
- **Oracle Autonomous Database Integration**: Connects to an Oracle Autonomous Database for secure and reliable data storage.
- **RESTful API**: Provides endpoints for:
  - **GET requests**: Retrieve user data from the database.
  - **PUT requests**: Update existing records.
  - **DELETE requests**: Remove records as needed.
- **JSON Responses**: Returns appropriate JSON responses for all API interactions, ensuring a standard format for data exchange.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Database Configuration](#database-configuration)
- [Packaging the Project](#packaging-the-project)
- [Creating a Docker Image](#creating-a-docker-image)
- [Uploading the Docker Image](#uploading-the-docker-image)
- [Running the Application](#running-the-application)

## Prerequisites

Before you begin, ensure you have the following:

- A Linux instance (Ubuntu, Fedora, etc.). Fedora was used on the project.
- Java Development Kit (JDK) installed
- Maven installed
- An Oracle Autonomous Database instance or any Database you wish to use, for this project an Oracle Autonomous Databse hosted in Oracle Cloud infrastructure was used
- Docker installed

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
For Fedora you can easily install Maven using yum::
```bash
sudo yum install -y maven
```

Check whether maven is installed with the command mvn. The command output should be as shown in the screenshot below
```bash
mvn
```

## Database Configuration
To connect your application to the Oracle Autonomous Database, you need to configure the database information in the application.yml file.
Create the file under src/main/resources, an example file is provided there named application.example.yml

### Add database configuration
Include database url, username and password, also add path to database Wallet in case you use it with your Oracle Autonomous Database.
Replace <DB_HOST>, <DB_USERNAME> and <DB_PASSWORD> with your actual database credentials.

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

## Creating a Docker Image
To create a Docker image for your application, follow these steps:
### Step 1: Move the Autonomous Database Wallet folder
If you are using an Oracle Wallet for database authentication, make sure to move the wallet folder to the target directory.

### Step 2: Modify the Dockerfile
Open the Dockerfile in your project directory and update the placeholder <WALLET_FOLDER> to the actual path of your wallet folder in the target directory

### Step 3: Build the Docker image
Run the following command from your project directory (where the Dockerfile is located):
```bash
docker build -t your-image-name .
```
Replace 'your-image-name' with a meaningful name for your Docker image.

## Uploading the Docker Image
Once you have created your Docker image, you can upload it to a container registry.
### Uploading to Oracle Cloud Infrastructure (OCI) Container Registry
### Step 1: Authenticate to OCI
```bash
oci setup config
```
Login into registry, The URL to log in to the registry is dependent on the OCI Region in which the repository is created. The Region Key for the region you are working can be identified from the OCI documentation page at https://docs.oracle.com/en-us/iaas/Content/General/Concepts/regions.html
```bash
sudo docker login <regionkey>.ocir.io
```
### Step 2: Tag Image on local repository
```bash
sudo docker tag your-image-name:version <region-key>.ocir.io/<RepositoryNamespace>/<repository-name>/<your-image-name>:<tag>
```
### Step 3: Push your Image
```bash
sudo docker push <imageid>
```

### Uploading to Docker Hub
### Step 1: Log in to Docker Hub:
```bash
docker login
```
### Step 2: Tag your image:

```bash
docker tag your-image-name your-dockerhub-username/your-image-name:<tag>
```
### Step 3: Push the image:

```bash
docker push your-dockerhub-username/your-image-name:<tag>
```
Replace 'your-dockerhub-username' and 'your-image-name' with your actual Docker Hub username and desired image name. Adjust <tag> as needed (e.g., latest).

## Running the Application

### To run your packaged application, use the following command:
```bash
java -jar target/Bookwarm 0.0.1-SNAPSHOT .jar
```



