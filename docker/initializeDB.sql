CREATE DATABASE db_name DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE
chess;

CREATE TABLE chessgame
(
    command_log  INT         NOT NULL AUTO_INCREMENT,
    room_id      INT         NOT NULL,
    target       VARCHAR(12) NOT NULL,
    destination  VARCHAR(12) NOT NULL,
    command_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (command_log)
);

INSERT INTO chessgame (command_log, room_id, target, destination, command_date)
VALUES (1, 99, 'a2', 'a4', '2021-04-01 12:35:01'),
       (2, 99, 'b7', 'b5', '2021-04-01 12:35:33'),
       (3, 99, 'a4', 'b5', '2021-04-01 12:36:18');

SELECT *
FROM chessgame;