GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS `chess` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE chess;

CREATE TABLE IF NOT EXISTS user
(
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS room
(
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(64) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);

CREATE TABLE IF NOT EXISTS movement
(
    source     VARCHAR(64) NOT NULL,
    target     VARCHAR(64) NOT NULL,
    room_id    INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (room_id) REFERENCES room (room_id)
);

