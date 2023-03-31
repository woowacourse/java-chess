CREATE TABLE chess_game
(
    id          INT         NOT NULL AUTO_INCREMENT,
    piece_type  VARCHAR(10) NOT NULL,
    piece_file  VARCHAR(10) NOT NULL,
    piece_rank  VARCHAR(10) NOT NULL,
    piece_color VARCHAR(10) NOT NULL,
    turn        VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
);