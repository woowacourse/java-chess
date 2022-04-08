drop table if exists piece cascade;
drop table if exists game cascade;

create table game
(
    no         int     not null auto_increment,
    white_turn boolean not null,
    primary key (no)
);

create table piece
(
    no       int        not null auto_increment,
    game_no  int        not null,
    position varchar(2) not null,
    type     varchar(6) not null,
    white    boolean    not null,
    primary key (no),
    unique key (game_no, position),
    foreign key (game_no) references game (no)
);