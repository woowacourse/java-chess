create table member
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

create table chessGame
(
    board_id INT,
    turn     varchar(10) not null,
    primary key (board_id)
);

create table piece
(
    piece_id INT,
    board_id INT,
    type     varchar(10) not null,
    team     varchar(10) not null,
    square   varchar(10) not null,
    constraint piece_PK primary key (piece_id, board_id)
);
