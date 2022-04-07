CREATE table board
(
    id   int         not null auto_increment primary key,
    turn varchar(10) not null
);

CREATE table piece
(
    id       int         not null auto_increment primary key,
    position varchar(2)  not null,
    board_id int         not null,
    name     varchar(10) not null,
    color    varchar(10) not null,
    foreign key (board_id) references board (id)
);
