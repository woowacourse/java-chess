CREATE TABLE chess_game
(
    piece_type VARCHAR(255),
    piece_rank       VARCHAR(255) NOT NULL,
    piece_file       VARCHAR(255) NOT NULL,
    team       VARCHAR(255),
    turn       VARCHAR(255) NOT NULL
);
