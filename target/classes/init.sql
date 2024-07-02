-- Drop tables if they exist
DROP TABLE IF EXISTS app_user CASCADE;
DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS expense CASCADE;
DROP TABLE IF EXISTS income CASCADE;

-- Create tables
CREATE TABLE app_user (
                          id BIGSERIAL PRIMARY KEY,
                          username VARCHAR(255) NOT NULL UNIQUE,
                          password VARCHAR(255) NOT NULL
);

CREATE TABLE category (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          user_id BIGINT NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES app_user(id)
);

CREATE TABLE expense (
                         id BIGSERIAL PRIMARY KEY,
                         amount DECIMAL(10, 2) NOT NULL,
                         description VARCHAR(255),
                         date TIMESTAMP NOT NULL,
                         category_id BIGINT,
                         user_id BIGINT NOT NULL,
                         FOREIGN KEY (category_id) REFERENCES category(id),
                         FOREIGN KEY (user_id) REFERENCES app_user(id)
);

CREATE TABLE income (
                        id BIGSERIAL PRIMARY KEY,
                        amount DECIMAL(10, 2) NOT NULL,
                        description VARCHAR(255),
                        date TIMESTAMP NOT NULL,
                        category_id BIGINT,
                        user_id BIGINT NOT NULL,
                        FOREIGN KEY (category_id) REFERENCES category(id),
                        FOREIGN KEY (user_id) REFERENCES app_user(id)
);

-- Insert initial data into app_user table
INSERT INTO app_user (username, password) VALUES
                                              ('john_doe', '$2a$10$7EqJtq98hPqEX7fNVaO/uu1hXKUt9iBOiSXYCx5Asa6el2fX4HzjG'), -- Password: password
                                              ('jane_doe', '$2a$10$7EqJtq98hPqEX7fNVaO/uu1hXKUt9iBOiSXYCx5Asa6el2fX4HzjG'); -- Password: password

-- Insert initial data into category table
INSERT INTO category (name, user_id) VALUES
                                         ('Groceries', 1),
                                         ('Utilities', 1),
                                         ('Salary', 2),
                                         ('Investments', 2);

-- Insert initial data into expense table
INSERT INTO expense (amount, description, date, category_id, user_id) VALUES
                                                                          (100.50, 'Weekly groceries', '2024-01-10 10:00:00', 1, 1),
                                                                          (200.00, 'Electricity bill', '2024-01-15 15:00:00', 2, 1);

-- Insert initial data into income table
INSERT INTO income (amount, description, date, category_id, user_id) VALUES
                                                                         (1500.00, 'Monthly salary', '2024-01-31 09:00:00', 3, 2),
                                                                         (300.00, 'Stock dividends', '2024-01-25 14:00:00', 4, 2);
