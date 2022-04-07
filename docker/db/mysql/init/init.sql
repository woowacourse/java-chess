create table member
(
    id   varchar(10) not null,
    name varchar(20) not null,
    primary key (id)
);

create table board
(
    id int not null auto_increment,
    turn varchar(10) not null,
    primary key (id)
);

create table piece
(
    id int not null auto_increment,
    board_id int not null,
    name varchar(20) not null,
    position varchar(10) not null,
    color varchar(10) not null,
    primary key (id),
    foreign key (board_id) references board (id)
);
