CREATE DATABASE stock_trading_db;
USE stock_trading_db;

-- Stocks table
CREATE TABLE stocks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    price DOUBLE
);

-- Users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    username VARCHAR(50) UNIQUE,
    balance DOUBLE
);

-- Portfolio table
CREATE TABLE portfolio (
    user_id INT,
    stock_id INT,
    quantity INT,
    available_balance DOUBLE,
    PRIMARY KEY (user_id, stock_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (stock_id) REFERENCES stocks(id)
);