
create table game
(
    id   int         not null unique auto_increment,
    turn varchar(10) not null
);


create table piece
(
    id       int         not null unique auto_increment,
    name     varchar(10) not null,
    color    varchar(10) not null,
    position varchar(10) not null unique,
    game_id  int         not null,
    foreign key (game_id) references game (id)
);