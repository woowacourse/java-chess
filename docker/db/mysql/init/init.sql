CREATE TABLE room
(
    id     bigint        NOT NULL,
    status varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE board
(
    id       bigint        NOT NULL AUTO_INCREMENT,
    position varchar(50) NOT NULL,
    symbol   varchar(50) NOT NULL,
    room_id  bigint        NOT NULL,
    PRIMARY KEY (id),
    KEY      room_id (room_id),
    CONSTRAINT id FOREIGN KEY (room_id) REFERENCES room (id) ON DELETE CASCADE ON UPDATE CASCADE
);
