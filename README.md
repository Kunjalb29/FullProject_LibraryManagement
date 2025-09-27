# ShelfSpace - Online Library Management System

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

A full-stack online library management system designed to streamline book lending and inventory management. Users can search for books, check their status, and manage their borrowed items through a personalized dashboard. This project is built with a modern, professional technology stack and includes a complete CI/CD pipeline for automated deployment.



## ✨ Key Features

* **User Authentication:** Secure user registration and login functionality.
* **Dynamic Book Catalog:** A filterable and searchable catalog of all books, fetched live from the database.
* **Book Details Page:** A dedicated page for each book with detailed information.
* **Borrow & Return System:** Interactive borrowing and returning of books (state managed).
* **User Dashboard:** A personalized dashboard for users to view their currently borrowed books and activity.
* **Full-Stack Integration:** A React frontend seamlessly communicating with a robust Spring Boot backend.

## 🛠️ Technology Stack

* **Frontend:** React, CSS Modules, React Router
* **Backend:** Java, Spring Boot, Spring Data JPA, Spring Security
* **Database:** MySQL
* **Containerization:** Docker, Docker Compose
* **CI/CD:** Jenkins
* **Deployment:** Amazon Web Services (AWS) EC2

## 📂 Project Structure
/
├── backend/      # Spring Boot Application (Java)
├── frontend/     # React Application
├── docker-compose.yml
├── Jenkinsfile
└── README.md

## 📋 Prerequisites

Before you begin, ensure you have the following installed on your local machine:
* Java Development Kit (JDK) 21 or later
* Apache Maven
* Node.js and npm
* MySQL Server and MySQL Workbench
* Docker Desktop

## 🚀 Local Development Setup

Follow these steps to get the project running on your local machine.

### Backend (Spring Boot)
1.  **Create Database:** In MySQL Workbench, create a new schema named `library_db`.
2.  **Configure:** Open the `backend/src/main/resources/application.properties` file and update the `spring.datasource.username` and `spring.datasource.password` with your local MySQL credentials.
3.  **Run:** Open the `backend` project in your IDE (like Eclipse) and run the main application class. The server will start on `http://localhost:8088`.

### Frontend (React)
1.  **Navigate to the frontend directory:**
    ```bash
    cd frontend
    ```
2.  **Install dependencies:**
    ```bash
    npm install
    ```
3.  **Run the development server:**
    ```bash
    npm run dev
    ```
4.  Open your browser and go to `http://localhost:5173`.

## 🐳 Running with Docker Compose

Once Docker Desktop is running, you can start the entire full-stack application with a single command from the project root directory.

1.  **Build and Run:**
    ```bash
    docker-compose up --build
    ```
2.  **Access:** The application will be available at `http://localhost`. The frontend is on port 80, the backend on 8088, and MySQL on 3306.

## ⚙️ CI/CD Pipeline (Jenkins)

This project includes a `Jenkinsfile` for automating the build, test, and deployment process. The pipeline consists of the following stages:

1.  **Clone Repository:** Fetches the latest code from the Git repository.
2.  **Build Docker Images:** Builds the `backend` and `frontend` Docker images.
3.  **Push Docker Images:** Pushes the built images to a container registry (e.g., Docker Hub).
4.  **Deploy to AWS:** Connects to the AWS EC2 instance via SSH, pulls the latest images, and runs `docker-compose up` to deploy the application.

## ☁️ AWS Deployment

The application is designed to be deployed to an AWS EC2 instance using the `docker-compose.yml` file.

1.  Launch an EC2 instance (Ubuntu/Amazon Linux).
2.  Install Docker and Docker Compose on the instance.
3.  Configure the instance's Security Group to allow inbound traffic on Port 80 (HTTP) and Port 22 (SSH).
4.  Clone the repository onto the EC2 instance.
5.  Run `docker-compose up -d` to start the application in the background.
