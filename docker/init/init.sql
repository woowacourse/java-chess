CREATE TABLE `board_statuses` (
                                  `board_id` int NOT NULL,
                                  `current_turn` varchar(10) NOT NULL,
                                  `is_over` char(1) NOT NULL,
                                  PRIMARY KEY (`board_id`)
);

CREATE TABLE `board_pieces` (
                                `board_id` int NOT NULL,
                                `position_file` int NOT NULL,
                                `position_rank` int NOT NULL,
                                `piece_type` varchar(10) NOT NULL,
                                `piece_camp` varchar(10) NOT NULL,
                                KEY `board_id` (`board_id`),
                                CONSTRAINT `fk_board_statuses_board_pieces` FOREIGN KEY (`board_id`)
                                REFERENCES `board_statuses` (`board_id`) ON UPDATE CASCADE
);


