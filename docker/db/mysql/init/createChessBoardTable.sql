DROP TABLE IF EXISTS chess_board;
SET character_set_client = utf8mb4;

CREATE TABLE chess_board
(
    x    VARCHAR(8)  NOT NULL,
    y    VARCHAR(8)  NOT NULL,
    side VARCHAR(8)  NOT NULL,
    type VARCHAR(12) NOT NULL,

    PRIMARY KEY (x,y)
)
