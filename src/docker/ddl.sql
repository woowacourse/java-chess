CREATE TABLE chess_game
(
    id              int             NOT NULL AUTO_INCREMENT,
    game_status     varchar(30)     NOT NULL,
    turn            varchar(30)     NOT NULL,
    primary key (`id`)
);

CREATE TABLE board
(
    id      int         NOT NULL,
    game_id int         NOT NULL,
    x_pos   int         NOT NULL,
    y_pos   int         NOT NULL,
    role    varchar(30) NOT NULL,
    team    varchar(30) NOT NULL,

    primary key (id, x_pos, y_pos),
    foreign key (game_id) references chess_game (id)
);
