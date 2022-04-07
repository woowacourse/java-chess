CREATE TABLE game
(
    game_id int         NOT NULL PRIMARY KEY,
    state   varchar(20) NOT NULL
);

CREATE TABLE piece
(
    piece_id   int         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    game_id    INT         NOT NULL,
    position   VARCHAR(10) NOT NULL,
    piece_type VARCHAR(10) NOT NULL,
    color      VARCHAR(10) NOT NULL,
    FOREIGN KEY (game_id)
        REFERENCES game (game_id) ON DELETE CASCADE
);
