GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS `chess` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE chess;

CREATE TABLE IF NOT EXISTS user
(
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name    CHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS room
(
    room_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name    CHAR(10) NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);

CREATE TABLE IF NOT EXISTS movement
(
    source     CHAR(2) NOT NULL,
    target     CHAR(2) NOT NULL,
    room_id    BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (room_id) REFERENCES room (room_id)
);

