CREATE TABLE game
(
    name                  varchar(10)  not null ,
    team_turn             varchar(10)  not null,
    primary key (name)
);

CREATE TABLE board
(
    board_name          varchar(15) not null,
    piece_file          varchar(10)         not null,
    piece_rank          varchar(10)         not null,
    piece_type          varchar(15) not null,
    piece_team          varchar(15) not null,
    FOREIGN KEY (board_name) REFERENCES game (name) on delete cascade ,
    PRIMARY KEY (board_name, piece_file, piece_rank)
);
