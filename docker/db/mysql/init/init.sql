CREATE TABLE `game`
(
    `current_turn` enum('WHITE_TURN', 'BLACK_TURN', 'END') NOT NULL,
    PRIMARY KEY (`current_turn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into game (current_turn)values ('WHITE_TURN');


CREATE TABLE `piece`
(
    `id`              bigint    NOT NULL AUTO_INCREMENT,
    `color`           varchar(8) NOT NULL,
    `piece_type`      varchar(8) NOT NULL,
    `position_column` varchar(8) NOT NULL,
    `position_row`    varchar(8) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=568 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

