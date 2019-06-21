create database wtc_chess_db char set utf8mb4 collate utf8mb4_general_ci;

use wtc_chess_db;

create table room(
	id int not null auto_increment,
    title varchar(255) not null,
    primary key(id)
);

create table board_state(
	id int not null auto_increment,
    type varchar(64) not null,
    loc_x varchar(16) not null,
    loc_y varchar(16) not null,
    room_id int not null,
    primary key(id),
    foreign key(room_id) references room(id) on delete cascade
);
