CREATE TABLE `game`
(
    `_id`         int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `gameName`    varchar(10),
    `currentTurn` tinyint
);

CREATE TABLE `moveHistory`
(
    `_id`           int PRIMARY KEY AUTO_INCREMENT,
    `source`        varchar(2),
    `target`        varchar(2),
    `pieceOnTarget` varchar(6),
    `g_id`          int
);

CREATE TABLE `board`
(
    `_id`    int PRIMARY KEY AUTO_INCREMENT,
    `square` varchar(2),
    `piece`  varchar(6),
    `camp`   tinyint,
    `g_id`   int
);

ALTER TABLE `board`
    ADD FOREIGN KEY (`g_id`) REFERENCES `game` (`_id`);

ALTER TABLE `moveHistory`
    ADD FOREIGN KEY (`g_id`) REFERENCES `game` (`_id`);
