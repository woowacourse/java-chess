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

CREATE TABLE piece (
    id int(10) not null auto_increment,
    type varchar(10) not null,
    color varchar(10) not null,
    primary key (id)
);

CREATE TABLE board (
    id int(10) not null auto_increment,
    piece_id int(10) not null,
    square varchar(10) not null,
    primary key (id),
    foreign key (piece_id) references piece (id)
);

CREATE TABLE game (
    id int(10) not null auto_increment,
    board_id int(10) not null,
    status varchar(10) not null,
    color varchar(10) not null,
    primary key (id),
    foreign key (board_id) references board (id)
);

INSERT INTO piece (type, color) VALUES
    ('pawn', 'black'),('bishop', 'black'),('rook', 'black'),('knight', 'black'), ('king', 'black'), ('queen', 'black'),
    ('pawn', 'white'),('bishop', 'white'),('rook', 'white'),('knight', 'white'), ('king', 'white'), ('queen', 'white'),
    ('empty', 'nothing');