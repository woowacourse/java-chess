create table board
(
    id     int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    status varchar(10) not null
);

create table square
(
    id          int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    square_file  int(10) NOT NULL,
    square_rank int(10) NOT NULL,
    board_id    int(10) NOT NULL,
    foreign key (board_id) references board (id)
        on delete cascade
);

create table piece
(
    id        int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type      varchar(10) NOT NULL,
    color     varchar(10) NOT NULL,
    square_id int(10) NOT NULL,
    foreign key (square_id) references square (id)
        on delete cascade
);

create table room
(
    id       int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title    varchar(20) NOT NULL,
    board_id int(10) NOT NULL,
    foreign key (board_id) references board (id)
        on delete cascade
);

create table member
(
    id       int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name     varchar(20) NOT NULL,
    room_id int(10) NOT NULL,
    foreign key (room_id) references room (id)
        on delete cascade
);
