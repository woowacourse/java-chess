create table piece
(
    id         varchar(2)  not null,
    piece_type varchar(20) not null,
    primary key (id)
);

create table turn
(
    id int auto_increment not null,
    turn_type varchar(10) not null,
    primary key (id)
);

insert into turn (turn_type) values ('ready');
