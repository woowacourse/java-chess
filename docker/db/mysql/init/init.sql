CREATE table member
(
    id   varchar(10) not null,
    name varchar(20) not null,
    primary key (id)
);

create table role
(
    user_id varchar(10) not null,
    role    varchar(10) not null,
    primary key (user_id),
    foreign key (user_id) references member (id)
);

CREATE table board
(
    id    int         not null auto_increment primary key,
    color varchar(10) not null
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
