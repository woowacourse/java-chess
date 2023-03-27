CREATE TABLE chess_game
(
    piece_type VARCHAR(255) NOT NULL,
    piece_rank TINYINT(10) NOT NULL,
    piece_file TINYINT(10) NOT NULL,
    team       VARCHAR(255) NOT NULL,
    turn       VARCHAR(255) NOT NULL
);
