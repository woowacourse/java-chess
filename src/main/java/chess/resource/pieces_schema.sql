DROP TABLE IF EXISTS pieces;

CREATE TABLE pieces
(
    board_file INT         NOT NULL,
    board_rank INT         NOT NULL,
    type       VARCHAR(64) NOT NULL,
    PRIMARY KEY (board_file, board_rank)
);

