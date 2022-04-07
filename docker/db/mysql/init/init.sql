create table board
(
    position varchar(10) not null,
    piece    varchar(20) not null,
    primary key (position)
);

create table turn
(
    team varchar(10) not null,
    primary key (team)
);


create table game_status
(
    value varchar(10) not null,
    primary key (value)
);


insert into turn (team) value ("white");

insert into game_status (value) value ("READY");