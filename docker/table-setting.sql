CREATE TABLE piece
(
    piece_id         INT          NOT NULL AUTO_INCREMENT,
    piece_type       VARCHAR(255) NOT NULL,
    piece_file       INT          NOT NULL,
    piece_rank       INT          NOT NULL,
    piece_type       INT          NOT NULL,
    piece_gameId       INT          NOT NULL,
    PRIMARY KEY (piece_id)
);

CREATE TABLE game
(
    game_id           INT         NOT NULL,
    turn              VARCHAR(255) NOT NULL,
    PRIMARY KEY (game_id)
);
