-- Drop existing tables if they exist
DROP TABLE IF EXISTS app_user CASCADE;
DROP TABLE IF EXISTS expense CASCADE;
DROP TABLE IF EXISTS income CASCADE;
DROP TABLE IF EXISTS balance CASCADE;

-- Create the app_user table
CREATE TABLE app_user (
                          id SERIAL PRIMARY KEY,
                          username VARCHAR(255) UNIQUE NOT NULL,
                          password VARCHAR(255) NOT NULL
);

-- Create the expense table
CREATE TABLE expense (
                         id SERIAL PRIMARY KEY,
                         amount DECIMAL(10, 2) NOT NULL,
                         description VARCHAR(255) DEFAULT '',
                         date TIMESTAMP NOT NULL,
                         category VARCHAR(255) NOT NULL,
                         user_id INTEGER REFERENCES app_user(id)
);

-- Create the income table
CREATE TABLE income (
                        id SERIAL PRIMARY KEY,
                        amount DECIMAL(10, 2) NOT NULL,
                        description VARCHAR(255) DEFAULT '',
                        date TIMESTAMP NOT NULL,
                        category VARCHAR(255) NOT NULL,
                        user_id INTEGER REFERENCES app_user(id)
);

-- Create the balance table
CREATE TABLE balance (
                         id SERIAL PRIMARY KEY,
                         net_balance DECIMAL(10, 2) DEFAULT 0,
                         total_income DECIMAL(10, 2) DEFAULT 0,
                         total_expense DECIMAL(10, 2) DEFAULT 0,
                         user_id INTEGER UNIQUE REFERENCES app_user(id)
);

-- Insert predefined categories into the expense table
INSERT INTO expense (amount, date, category, user_id) VALUES
                                                          (0, NOW(), 'Food & Drink', 1),
                                                          (0, NOW(), 'Shopping', 1),
                                                          (0, NOW(), 'Transport', 1),
                                                          (0, NOW(), 'Home', 1),
                                                          (0, NOW(), 'Bills & Fees', 1),
                                                          (0, NOW(), 'Entertainment', 1),
                                                          (0, NOW(), 'Car', 1),
                                                          (0, NOW(), 'Travel', 1),
                                                          (0, NOW(), 'Family & Personal', 1),
                                                          (0, NOW(), 'Healthcare', 1),
                                                          (0, NOW(), 'Education', 1),
                                                          (0, NOW(), 'Groceries', 1),
                                                          (0, NOW(), 'Gifts', 1),
                                                          (0, NOW(), 'Sport & Hobbies', 1),
                                                          (0, NOW(), 'Beauty', 1),
                                                          (0, NOW(), 'Work', 1),
                                                          (0, NOW(), 'Others', 1);

-- Insert predefined categories into the income table
INSERT INTO income (amount, date, category, user_id) VALUES
                                                         (0, NOW(), 'Salary', 1),
                                                         (0, NOW(), 'Business', 1),
                                                         (0, NOW(), 'Gifts', 1),
                                                         (0, NOW(), 'Extra Income', 1),
                                                         (0, NOW(), 'Loan', 1),
                                                         (0, NOW(), 'Parental Leave', 1),
                                                         (0, NOW(), 'Insurance Payment', 1),
                                                         (0, NOW(), 'Others', 1);

-- Insert initial user balance
INSERT INTO balance (net_balance, total_income, total_expense, user_id) VALUES
    (0, 0, 0, 1);
