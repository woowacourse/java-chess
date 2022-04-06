create table board
(
    id         int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    room_title varchar(10) not null
);

create table square
(
    id       int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    square_file     int(10) NOT NULL,
    square_rank     int(10) NOT NULL,
    board_id int(10) NOT NULL,
    foreign key (board_id) references board (id)
);

create table piece
(
    id       int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type     int(10) NOT NULL,
    color     int(10) NOT NULL,
    square_id int(10) NOT NULL,
    foreign key (square_id) references square (id)
);

create table member
(
    id       int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(20) NOT NULL,
    board_id int(10) NOT NULL,
    foreign key (board_id) references board (id)
);
