CREATE TABLE movement
(
    source     VARCHAR(64) NOT NULL,
    target     VARCHAR(64) NOT NULL,
    room_id    INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (room_id) REFERENCES room (room_id)
);

