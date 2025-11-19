CREATE DATABASE  online_shopping;
USE online_shopping;

CREATE TABLE users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    email VARCHAR(100),
    address VARCHAR(255)
);

CREATE TABLE  admin(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    password VARCHAR(100)
);

CREATE TABLE  products(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200),
    description VARCHAR(500),
    price DOUBLE,
    quantity INT,
    image_path VARCHAR(500)
);

CREATE TABLE  cart(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    product_id INT,
    qty INT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE TABLE  orders(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    total DOUBLE,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE  order_items(
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    qty INT,
    price DOUBLE,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- sample data
INSERT IGNORE INTO admin(username, password) VALUES('admin','admin123');
INSERT IGNORE INTO users(username,password,email,address) VALUES('user1','pass1','user1@example.com','India');
INSERT IGNORE INTO products(name,description,price,quantity,image_path) VALUES
('Laptop','High performance laptop',55000,10,''),
('Mobile Phone','Android smartphone',15000,25,''),
('Headphones','Noise cancelling',1200,50,''),
('Smart Watch','Fitness watch',3500,20,'');
