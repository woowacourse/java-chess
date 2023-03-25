CREATE TABLE game
(
    name                  varchar(10)  not null ,
    team_turn             varchar(10)  not null,
    primary key (name)
);

CREATE TABLE board
(
    `piece_id` bigint     not null unique auto_increment,
    `board_name`          varchar(15) not null,
    `file`                int         not null,
    `rank`                int         not null,
    `piece`               varchar(15) not null,
    `team`                varchar(15) not null,
    FOREIGN KEY (board_name) REFERENCES `game` (name),
    PRIMARY KEY (piece_id)
);
