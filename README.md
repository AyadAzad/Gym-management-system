# Gym Management System

This Java Swing project with JDBC and MySQL integration is a Gym Management System. It provides a graphical user interface for managing members, coaches, and payments in a gym setting. The system is designed with a responsive and visually appealing layout using the Swing library, and it interacts with a MySQL database using JDBC for data storage.

## Features

- **Add Member**: Allows the addition of new gym members.
- **Remove Member**: Enables the removal of existing gym members.
- **View Members**: Displays a list of all gym members.
- **Add Coach**: Allows the addition of new coaches.
- **Remove Coach**: Enables the removal of existing coaches.
- **View Coaches**: Displays a list of all coaches.
- **Payment Form**: Provides a form for handling payments from members.
- **Payment Manager**: Manages the overall payment system.

## Getting Started

### Prerequisites

- Ensure you have Java installed on your system.
- Set up a MySQL database and update the connection details in the code.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/gym-management-system.git
   ```

2. Open the project in your preferred Java IDE.

3. Update the MySQL database connection details in the code.

4. Compile and run the `mainWindow` class.

## Usage

- Upon running the application, the main window will appear with a grid of buttons representing different functionalities.
- Click on the respective buttons to perform actions such as adding/removing members, viewing member/coach lists, and managing payments.
- Hover effects have been implemented for an improved user experience.

## Database Configuration

Ensure that you have set up your MySQL database and updated the connection details in the code. Modify the following section in the `mainWindow` class:

```java
// Database connection details
String url = "jdbc:mysql://localhost:3306/your_database_name";
String username = "your_username";
String password = "your_password";
```

## Contributing

Feel free to contribute to the project by submitting pull requests or reporting issues.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments

- Thanks to the Swing library for providing a robust framework for building graphical user interfaces.
- JDBC and MySQL for database connectivity.

---

**Note:** Replace placeholders such as `your_database_name`, `your_username`, and `your_password` with your actual database details. The project structure and additional classes (`addMember`, `viewMember`, etc.) referenced in the code are assumed to exist and handle specific functionalities.
