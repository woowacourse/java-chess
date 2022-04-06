CREATE TABLE `game`
(
    `id`    int         NOT NULL AUTO_INCREMENT,
    `state` varchar(10) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `board`
(
    `color`    varchar(10) NOT NULL,
    `position` varchar(2)  NOT NULL,
    `piece`    varchar(10) NOT NULL,
    `game_id`  int         NOT NULL,
    PRIMARY KEY (`position`, `game_id`),
    KEY        `board_game_id_fk` (`game_id`),
    CONSTRAINT `board_game_id_fk` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

