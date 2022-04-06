create table Game
(
    id     bigint primary key,
    status boolean     not null,
    turn   varchar(45) not null
);

create table Board
(
    id       bigint primary key not null,
    game_id  bigint             not null,
    position varchar(5)         not null,
    piece    varchar(20)        not null,
    color    varchar(15)        not null,
    foreign key (game_id) references game (id)
);