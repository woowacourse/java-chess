create table player
(
    id   int not null auto_increment primary key,
    name varchar(12) not null,
    win  int default 0,
    draw int default 0,
    lose int default 0
);

create table board
(
    id        int not null auto_increment primary key,
    player_id int not null,
    turn      varchar(5) not null,
    foreign key (player_id) references player(id)
);

create table piece
(
    id       int not null auto_increment primary key,
    board_id int not null,
    position varchar(2) not null,
    color    varchar(5) not null,
    role     varchar(1) not null,
    foreign key (board_id) references board(id)
);
