CREATE TABLE Expense (
    id INT PRIMARY KEY AUTO_INCREMENT,
    amount DOUBLE NOT NULL,
    category_id INT,
    date VARCHAR(20),
    FOREIGN KEY (category_id) REFERENCES ExpenseCategory(id)
);
