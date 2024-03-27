CREATE TABLE room
(
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(64) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);
