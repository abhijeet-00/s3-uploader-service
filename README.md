# S3 Uploader Service

A Spring Boot service to upload files to AWS S3 using environment variables for authentication.

## Features
- Upload files to AWS S3
- Configurable via environment variables
- Handles large file uploads efficiently

## Prerequisites
- Java 17+
- Maven
- AWS Account with S3 bucket

## Setup / Installation
1. Clone the repository:  
   ```bash
   git clone https://github.com/abhijeet-00/s3-uploader-service.git
Set environment variables:

AWS_ACCESS_KEY_ID

AWS_SECRET_ACCESS_KEY

AWS_REGION (optional, default region)

Build and run the project:

bash
Copy
Edit
mvn clean install
java -jar target/s3-uploader-service-0.0.1-SNAPSHOT.jar
Usage
Make POST requests to /upload endpoint with a file using any HTTP client (Postman, curl, etc.).

Example using curl:

bash
Copy
Edit
curl -F "file=@path/to/your/file.txt" http://localhost:8080/upload
