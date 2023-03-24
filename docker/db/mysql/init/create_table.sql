CREATE TABLE `room`
(
    `_id`         int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `gameName`    varchar(10),
    `currentTurn` varchar(5) default 'WHITE'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `moveHistory`
(
    `_id`           int PRIMARY KEY AUTO_INCREMENT,
    `source`        varchar(2),
    `target`        varchar(2),
    `pieceOnTarget` varchar(6),
    `r_id`          int
);

CREATE TABLE `board`
(
    `_id`    int PRIMARY KEY AUTO_INCREMENT,
    `square` varchar(2),
    `piece`  varchar(6),
    `camp`   varchar(5),
    `r_id`   int
);

ALTER TABLE `board`
    ADD FOREIGN KEY (`r_id`) REFERENCES `game` (`_id`);

ALTER TABLE `moveHistory`
    ADD FOREIGN KEY (`r_id`) REFERENCES `game` (`_id`);
