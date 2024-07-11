-- Drop existing tables if they exist
DROP TABLE IF EXISTS app_user CASCADE;
DROP TABLE IF EXISTS expense CASCADE;
DROP TABLE IF EXISTS income CASCADE;

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


-- Insert predefined categories into the expense table
INSERT INTO expense (amount, date, category) VALUES
                                                 (0, NOW(), 'Food & Drink'),
                                                 (0, NOW(), 'Shopping'),
                                                 (0, NOW(), 'Transport'),
                                                 (0, NOW(), 'Home'),
                                                 (0, NOW(), 'Bills & Fees'),
                                                 (0, NOW(), 'Entertainment'),
                                                 (0, NOW(), 'Car'),
                                                 (0, NOW(), 'Travel'),
                                                 (0, NOW(), 'Family & Personal'),
                                                 (0, NOW(), 'Healthcare'),
                                                 (0, NOW(), 'Education'),
                                                 (0, NOW(), 'Groceries'),
                                                 (0, NOW(), 'Gifts'),
                                                 (0, NOW(), 'Sport & Hobbies'),
                                                 (0, NOW(), 'Beauty'),
                                                 (0, NOW(), 'Work'),
                                                 (0, NOW(), 'Others');

-- Insert predefined categories into the income table
INSERT INTO income (amount, date, category) VALUES
                                                (0, NOW(), 'Salary'),
                                                (0, NOW(), 'Business'),
                                                (0, NOW(), 'Gifts'),
                                                (0, NOW(), 'Extra Income'),
                                                (0, NOW(), 'Loan'),
                                                (0, NOW(), 'Parental Leave'),
                                                (0, NOW(), 'Insurance Payment'),
                                                (0, NOW(), 'Others');
