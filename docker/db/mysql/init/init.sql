create table member
(
    id   varchar(10) not null,
    name varchar(20) not null,
    primary key (id)
);

create table role(
    member_id varchar(10) not null,
    role varchar(10) not null,
    primary key (member_id),
    foreign key (member_id) references member (id)
);

create table game
(
    id int not null,
    id_white_player   varchar(20) not null,
    id_black_player   varchar(20) not null,
    primary key (id)
);

create table board
(
    id int not null AUTO_INCREMENT,
    game_id   int not null,
    position   varchar(20) not null,
    piece   varchar(20) not null,
    primary key (id),
    foreign key (game_id) references game (id)
);