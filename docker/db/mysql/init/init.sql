-- create table member (
--   id varchar(10) not null,
--   name varchar(20) not null,
--   primary key (id)
-- );
--
-- create table role (
--     user_id varchar(10) not null,
--     role varchar(10) not null,
--     primary key (user_id),
--     foreign key (user_id) references member (id)
-- );

create table neo_board
(
    id         int(10)                     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    room_title varchar(10)                 not null,
    turn       varchar(10) DEFAULT 'WHITE' not null
);

create table neo_position
(
    id              int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    position_column int(10) NOT NULL,
    position_row    int(10) NOT NULL,
    board_id        int(10) NOT NULL,
    foreign key (board_id) references neo_board (id)
    on delete cascade
);

create table neo_piece
(
    id          int(10)     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type        varchar(10) NOT NULL,
    color       varchar(10) NOT NULL,
    position_id int(10)     NOT NULL,
    foreign key (position_id) references neo_position (id)
    on delete cascade
);
