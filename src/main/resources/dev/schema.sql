CREATE TABLE chess_log(
    turn INT UNSIGNED NOT NULL PRIMARY KEY,
    position_from VARCHAR(2) NOT NULL,
    position_to VARCHAR(2) NOT NULL
);