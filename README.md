# BUDGET BUDDY

## Overview
The Simple Budget Buddy is a Java application that allows users to manage and track their expenses. It comprises four main components: `User`, `UserManager`, `Expense`, `ExpenseManager`, and `ExpenseCategory`. Here's a concise description of each component:

### User and UserManager
The `User` class represents application users, storing their username, email, password, and a list of expenses.

The `UserManager` class effectively manages user data, utilizing a map to map usernames to `User` objects. It provides functions for user registration, retrieval by username, and username availability checks.

### Expense and ExpenseManager
`Expense` instances represent individual expenses and include a unique ID, amount, category, date, and a reference to the user who created the expense.

The `ExpenseManager` allows users to add expenses and provides methods for accessing a user's expenses and all expenses in the application.

### ExpenseCategory
The `ExpenseCategory` class maintains a predefined list of expense categories as an immutable set. This facilitates the selection of categories when adding expenses.

## Application Overview
This Java application is designed to help users register, add expenses with specific categories, and efficiently track those expenses. It offers some key features and design considerations:

- Utilizes predefined expense categories to ensure consistency when adding expenses.
- Implements a robust mechanism for generating unique IDs for expenses via a global counter in the `Expense` class.
- Establishes a clear relationship between users and their respective expenses through user references in each expense.

## Getting Started
1. Clone this repository to your local machine.

2. Compile the Java files using your preferred Java compiler, e.g.,

    ```shell
    javac Main.java
    ```

3. Run the application:

    ```shell
    java Main
    ```

4. Follow the application's prompts to register users and add expenses.

## Additional Features
While this implementation serves as a fundamental expense tracking system, you can extend it with various features to meet your specific needs. Consider adding reporting, statistical analysis, and robust user authentication for a comprehensive expense management application. Ensure to address security and error-handling concerns in your development process.
