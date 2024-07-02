-- Drop existing tables if they exist
DROP TABLE IF EXISTS app_user CASCADE;
DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS expense CASCADE;
DROP TABLE IF EXISTS income CASCADE;

-- Create the app_user table
CREATE TABLE app_user (
                          id SERIAL PRIMARY KEY,
                          username VARCHAR(255) UNIQUE NOT NULL,
                          password VARCHAR(255) NOT NULL
);

-- Create the category table
CREATE TABLE category (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          user_id INTEGER REFERENCES app_user(id)
);

-- Create the expense table
CREATE TABLE expense (
                         id SERIAL PRIMARY KEY,
                         amount DECIMAL(10, 2) NOT NULL,
                         description VARCHAR(255),
                         date TIMESTAMP NOT NULL,
                         category_id INTEGER REFERENCES category(id),
                         user_id INTEGER REFERENCES app_user(id)
);

-- Create the income table
CREATE TABLE income (
                        id SERIAL PRIMARY KEY,
                        amount DECIMAL(10, 2) NOT NULL,
                        description VARCHAR(255),
                        date TIMESTAMP NOT NULL,
                        category_id INTEGER REFERENCES category(id),
                        user_id INTEGER REFERENCES app_user(id)
);

-- -- Insert initial data
-- -- Insert an admin user
-- INSERT INTO app_user (username, password) VALUES ('admin', '$2a$10$D9Qf1L8I5mE5uIYF0kXKUu6K0y4y4K5mZkqfJj1mH6ZvQ/4/d7/zK'); -- password: 1234
--
-- -- Insert some categories
-- INSERT INTO category (name, user_id) VALUES ('Groceries', 1);
-- INSERT INTO category (name, user_id) VALUES ('Salary', 1);
-- INSERT INTO category (name, user_id) VALUES ('Entertainment', 1);
--
-- -- Insert some expenses
-- INSERT INTO expense (amount, description, date, category_id, user_id) VALUES (50.00, 'Grocery shopping', NOW(), 1, 1);
-- INSERT INTO expense (amount, description, date, category_id, user_id) VALUES (20.00, 'Movie night', NOW(), 3, 1);
--
-- -- Insert some income
-- INSERT INTO income (amount, description, date, category_id, user_id) VALUES (1000.00, 'Monthly salary', NOW(), 2, 1);
-- INSERT INTO income (amount, description, date, category_id, user_id) VALUES (150.00, 'Freelance work', NOW(), 2, 1);
