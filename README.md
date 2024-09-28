# Book Record Management System

## Description

The Book Record Management System is a Java application built using Swing for the GUI and MySQL for data storage. It allows users to manage book records effectively by providing functionalities to add, update, delete, and view books.

## Features

- **Add New Books**: 
  - Users can input details such as Book ID, Title, Price, Author, and Publisher through an intuitive form. Upon submission, the application validates the input and stores the book record in the database.

- **Update Existing Books**: 
  - Users can select a book from the displayed list and modify its details. The application retrieves the existing record, populates the form with the current information, and allows the user to make changes. Once the user submits the form, the updated information is saved to the database.

- **Delete Books**: 
  - Users can easily remove a book from the system. By selecting a book from the list and clicking the "Delete" button, the application prompts for confirmation before permanently removing the record from the database.

- **View All Books**: 
  - The main interface displays a table containing all the books in the database, including their Book ID, Title, Price, Author, and Publisher. Users can scroll through the list to see all available records at a glance.

- **User-Friendly Interface**: 
  - The application features a clean and simple GUI built with Swing, ensuring that even users with minimal technical knowledge can navigate and utilize the system efficiently.

- **Database Connectivity**: 
  - The application uses JDBC to connect to a MySQL database, allowing for persistent storage of book records and facilitating CRUD (Create, Read, Update, Delete) operations seamlessly.

### Prerequisites

- **Java Development Kit (JDK) 8 or higher**: Ensure you have Java installed on your machine.
- **MySQL Server**: Required for storing book records.
- **MySQL Connector/J**: The JDBC driver for MySQL.
