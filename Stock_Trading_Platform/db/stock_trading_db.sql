-- Create database
CREATE DATABASE IF NOT EXISTS stock_trading_db;
USE stock_trading_db;

-- =========================
-- USERS TABLE
-- =========================
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    balance DOUBLE NOT NULL
);

-- Insert users (realistic names)
INSERT INTO users (name, username, balance) VALUES
('Amit Sharma', 'amit_sharma', 50000),
('Riya Patel', 'riya_patel', 60000),
('Rahul Verma', 'rahul_verma', 45000),
('Sneha Kulkarni', 'sneha_k', 70000),
('Karan Mehta', 'karan_mehta', 55000),
('Neha Singh', 'neha_singh', 65000),
('Arjun Rao', 'arjun_rao', 48000),
('Pooja Nair', 'pooja_nair', 52000),
('Vikram Joshi', 'vikram_j', 75000),
('Ananya Das', 'ananya_das', 80000);

-- =========================
-- STOCKS TABLE
-- =========================
CREATE TABLE IF NOT EXISTS stocks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price DOUBLE NOT NULL
);

-- Insert stocks
INSERT INTO stocks (name, price) VALUES
('TCS', 3800),
('Infosys', 1600),
('Wipro', 420),
('HDFC Bank', 1550),
('ICICI Bank', 980),
('Reliance', 2500),
('Adani Ports', 1150),
('ITC', 450),
('Axis Bank', 1100),
('LT', 3600);

-- =========================
-- PORTFOLIO TABLE
-- =========================
CREATE TABLE IF NOT EXISTS portfolio (
    user_id INT,
    stock_id INT,
    quantity INT DEFAULT 0,
    available_balance DOUBLE NOT NULL,
    PRIMARY KEY (user_id, stock_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (stock_id) REFERENCES stocks(id)
);

-- Insert portfolio data
-- Each user initially has balance assigned per stock
INSERT INTO portfolio (user_id, stock_id, quantity, available_balance) VALUES
(1, 1, 5, 50000),
(1, 2, 3, 50000),
(2, 3, 10, 60000),
(2, 4, 2, 60000),
(3, 5, 4, 45000),
(4, 6, 6, 70000),
(5, 7, 3, 55000),
(6, 8, 8, 65000),
(7, 9, 2, 48000),
(8, 10, 1, 52000);
