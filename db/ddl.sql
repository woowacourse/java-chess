CREATE TABLE board
(
    `board_id` VARCHAR(12) NOT NULL UNIQUE,
    `color`    varchar(12) not null,
    PRIMARY KEY (board_id)
);

CREATE TABLE boardInformation
(
    `boardinformation_id` bigint      not null unique auto_increment,
    `column_`             int         not null,
    `row_`                int         not null,
    `piece_type`          varchar(12) not null,
    `piece_color`         varchar(12) not null,
    `board_id`            varchar(12) not null,
    FOREIGN KEY (board_id) REFERENCES `board` (board_id),
    PRIMARY KEY (boardInformation_id)
);

CREATE TABLE player
(
    `player_id` varchar(12) not null unique,
    `board_id`  varchar(12) not null,
    FOREIGN KEY (board_id) REFERENCES `board` (board_id),
    PRIMARY KEY (player_id)
);
