DROP TABLE IF EXISTS pieces;

CREATE TABLE pieces
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    board_file INT         NOT NULL,
    board_rank INT         NOT NULL,
    type       VARCHAR(64) NOT NULL
);

