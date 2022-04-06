create table board
(
    id         int(10)                     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    room_title varchar(10)                 not null,
    turn       varchar(10) DEFAULT 'WHITE' not null
);

create table position
(
    id              int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    position_column int(10) NOT NULL,
    position_row    int(10) NOT NULL,
    board_id        int(10) NOT NULL,
    foreign key (board_id) references board (id)
        on delete cascade
);

create table piece
(
    id          int(10)     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type        varchar(10) NOT NULL,
    color       varchar(10) NOT NULL,
    position_id int(10)     NOT NULL,
    foreign key (position_id) references position (id)
        on delete cascade
);

create table member
(
    id       int(10)     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name     varchar(20) NOT NULL,
    board_id int(10)     NOT NULL,
    foreign key (board_id) references board (id)
        on delete cascade
);
