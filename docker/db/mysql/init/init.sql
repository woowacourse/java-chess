create table member
(
    id varchar(10) not null,
    name varchar(20) not null,
    primary key (id)
);

create table role(
    user_id varchar(10) not null,
    role varchar(10) not null,
    primary key (user_id),
    foreign key (user_id) references member (id)
);

create table chessgame(
    id int not null auto_increment,
    state varchar(9) not null,
    primary key (id)
);

create table chesspiece(
    id int not null auto_increment,
    name varchar(6) not null,
    team varchar(5) not null,
    primary key (id)
);

create table chessboard(
    id int not null auto_increment,
    board_row int not null,
    board_column varchar(1) not null,
    chesspiece_id int not null,
    chessgame_id int not null,
    primary key (id),
    foreign key (chesspiece_id) references chesspiece (id),
    foreign key (chessgame_id) references chessgame (id)
);

insert into chesspiece (name, team)
values
    ("pawn", "black"),
    ("pawn", "white"),
    ("rook", "black"),
    ("rook", "white"),
    ("bishop", "black"),
    ("bishop", "white"),
    ("knight", "black"),
    ("knight", "white"),
    ("queen", "black"),
    ("queen", "white"),
    ("king", "black"),
    ("king", "white")
    ;
