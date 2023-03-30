CREATE TABLE IF NOT EXISTS `game` (
    `game_id` bigint NOT NULL AUTO_INCREMENT,
    `turn` varchar(45) NOT NULL,
    `is_end` tinyint NOT NULL,
    PRIMARY KEY (`game_id`)
);

CREATE TABLE IF NOT EXISTS `piece` (
    `piece_id` bigint NOT NULL AUTO_INCREMENT,
    `type` varchar(45) NOT NULL,
    `position` varchar(45) NOT NULL,
    `color` varchar(45) NOT NULL,
    `game_id` bigint NOT NULL,
    PRIMARY KEY (`piece_id`),
    FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON UPDATE CASCADE
);