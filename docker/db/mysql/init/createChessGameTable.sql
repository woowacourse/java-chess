DROP TABLE IF EXISTS chess_game;
SET character_set_client = utf8mb4;

CREATE TABLE chess_game
(
    turn VARCHAR(8) NOT NULL
)