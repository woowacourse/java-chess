create table board
(
    id int not null auto_increment,
    turn int not null,
    primary key (id)
);

create table piece
(
    id int not null auto_increment,
    board_id int not null,
    position varchar(2) not null,
    type varchar(6) not null,
    primary key (id),
    foreign key (board_id) references board (id)
);