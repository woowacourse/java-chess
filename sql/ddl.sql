USE test;

CREATE TABLE room (
    room_id VARCHAR(12) NOT NULL,
    turn VARCHAR(6) NOT NULL,
    state JSON NOT NULL,
    PRIMARY KEY (room_id)
);