USE chess;

CREATE TABLE room
(
    name         VARCHAR(10) NOT NULL UNIQUE,
    game_status  VARCHAR(10) NOT NULL,
    current_turn VARCHAR(10) NOT NULL,
    PRIMARY KEY (name)
);

CREATE TABLE chess_piece
(
    chess_piece_id INT         NOT NULL UNIQUE AUTO_INCREMENT,
    room_name      VARCHAR(10) NOT NULL,
    position       VARCHAR(10) NOT NULL,
    chess_piece    VARCHAR(10) NOT NULL,
    color          VARCHAR(10) NOT NULL,
    PRIMARY KEY (chess_piece_id),
    FOREIGN KEY (room_name) REFERENCES Room (name) ON DELETE CASCADE
);
