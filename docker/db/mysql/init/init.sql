drop table board;
drop table game;

create table game
(
    id int not null,
    id_white_player   varchar(20) not null,
    id_black_player   varchar(20) not null,
    turn varchar(10) not null,
    primary key (id)
);

create table board
(
    id int not null AUTO_INCREMENT,
    game_id   int not null,
    position   varchar(20) not null,
    piece   varchar(20) not null,
    primary key (id),
    foreign key (game_id) references game (id)
);
