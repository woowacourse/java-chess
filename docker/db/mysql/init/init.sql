CREATE TABLE room
(
    id     int        NOT NULL,
    status varchar(5) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY `room_id_UNIQUE` (`id`)
);

CREATE TABLE board
(
    id       int        NOT NULL AUTO_INCREMENT,
    position varchar(5) NOT NULL,
    symbol   varchar가(5) NOT NULL,
    room_id  int        NOT NULL,
    PRIMARY KEY (id, room_id),
    KEY      room_id (room_id),
    CONSTRAINT id FOREIGN KEY (room_id) REFERENCES room (id) ON DELETE CASCADE ON UPDATE CASCADE
);

