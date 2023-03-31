CREATE TABLE room
(
    room_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(30) NOT NULL
);

CREATE TABLE piece
(
    piece_id      BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    room          BIGINT(20)  NOT NULL,
    name          VARCHAR(10) NOT NULL,
    team          VARCHAR(10) NOT NULL,
    position_file VARCHAR(10) NOT NULL,
    position_rank VARCHAR(10) NOT NULL,
    FOREIGN KEY (room) REFERENCES room (room_id) ON DELETE CASCADE
);
