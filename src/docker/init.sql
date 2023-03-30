CREATE TABLE chess_game
(
    id   INT         NOT NULL AUTO_INCREMENT,
    turn VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE piece
(
    chess_game_id INT         NOT NULL,
    id            INT         NOT NULL AUTO_INCREMENT,
    `file`         VARCHAR(50) NOT NULL,
    `rank`        VARCHAR(50) NOT NULL,
    side          VARCHAR(50) NOT NULL,
    type          VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (chess_game_id) REFERENCES chess_game (id)
);