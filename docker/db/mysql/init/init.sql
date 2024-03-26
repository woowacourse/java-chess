USE chess;

CREATE TABLE IF NOT EXISTS game_information
(
    game_id            int        NOT NULL AUTO_INCREMENT,
    current_turn_color VARCHAR(5) NOT NULL,
    PRIMARY KEY (game_id)
);

CREATE TABLE IF NOT EXISTS chessboard
(
    id      int         NOT NULL AUTO_INCREMENT,
    file    VARCHAR(64) NOT NULL,
    `rank`  int         NOT NULL,
    type    VARCHAR(10) NOT NULL,
    color   VARCHAR(5)  NOT NULL,
    game_id int         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES game_information (game_id) ON DELETE CASCADE
);
