create table chess_game
(
    chess_game_id int AUTO_INCREMENT NOT NULL,
    turn          VARCHAR(40) NOT NULL,
    PRIMARY KEY (chess_game_id)
);

create table chess_board
(
    chess_board_id int AUTO_INCREMENT NOT NULL,
    chess_game_id  int         NOT NULL,
    piece_type     VARCHAR(40) NOT NULL,
    piece_rank     int         NOT NULL,
    piece_file     int         NOT NULL,
    team           VARCHAR(40) NOT NULL,
    PRIMARY KEY (chess_board_id),
    FOREIGN KEY (chess_game_id) REFERENCES chess_game (chess_game_id) ON DELETE CASCADE
);
