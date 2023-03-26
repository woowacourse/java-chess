DROP TABLE IF EXISTS chess_game CASCADE;

CREATE TABLE IF NOT EXISTS chess_game
(
    piece_type  varchar(255) NOT NULL,
    piece_file  varchar(255) NOT NULL,
    piece_rank  varchar(255) NOT NULL,
    piece_team  varchar(255) NOT NULL,
    game_status varchar(255) NOT NULL,
    turn        varchar(255) NOT NULL
);
