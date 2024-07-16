# 🛍️ Product Store

## 📖 Introduction

This application is a comprehensive product management system built with Java, Spring Boot, Maven, and a frontend developed with JavaScript. It allows users to create, read, update, and delete products. Additionally, it provides admin and user management functionalities and a user-friendly interface for easy navigation and usage.

## 🚀 Features

### 📦 Product Management

- **Create Product**: This feature allows you to create a new product. You need to provide the product name, price, and quantity.

- **Get Product**: This feature allows you to retrieve the details of a specific product by providing the product name.

- **Update Product**: This feature allows you to update the details of a specific product. You need to provide the current product name, new product name, new price, and new quantity.

- **Delete Product**: This feature allows you to delete a specific product by providing the product name.

- **Update Product Stock**: This feature allows you to update the stock of a specific product. You need to provide the product name and the new quantity.

### 👨‍💼 Admin Management

- **Create Admin**: This feature allows you to create a new admin. You need to provide the admin email and password.

- **Get Admin**: This feature allows you to retrieve the details of a specific admin by providing the admin email.

- **Update Admin**: This feature allows you to update the details of a specific admin. You need to provide the current admin email, new admin email, and new password.

### 👥 User Management

- **Create User**: This feature allows you to create a new user. You need to provide the user email and password.

- **Get User**: This feature allows you to retrieve the details of a specific user by providing the user email.

- **Update User**: This feature allows you to update the details of a specific user. You need to provide the current user email, new user email, and new password.

- **Update User Credit**: This feature allows you to update the credit of a specific user. You need to provide the user email and the new credit.

- **Delete User**: This feature allows you to delete a specific user by providing the user email.

- **Buy**: This feature allows a user to make a purchase. You need to provide the user email.

### 🖥️ Frontend

- **Product Page**: This page displays the details of a specific product. Users can view the product name, price, and quantity.

- **Admin Page**: This page allows admins to manage products and users. Admins can create, update, and delete products and users.

- **User Page**: This page allows users to view and manage their account details. Users can update their email, password, and credit.

## 📚 How to Use

1. **Clone the repository**: First, you need to clone the repository to your local machine.

2. **Build the project**: Navigate to the project directory and run `mvn clean install` to build the project.

3. **Run the application**: Run the application using the command `mvn spring-boot:run`.

4. **Access the endpoints**: Once the application is running, you can access the endpoints using any HTTP client like Postman. The base URL for the application is `http://localhost:8080`.

5. **Access the frontend**: Open your web browser and navigate to `http://localhost:8080` to access the frontend of the application.