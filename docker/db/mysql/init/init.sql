create table board
(
    id    int         not null auto_increment primary key,
    state varchar(10) not null
);

create table piece
(
    id         int        not null auto_increment primary key,
    piece_type varchar(1) not null,
    position   varchar(2) not null,
    color      varchar(5) not null
);
