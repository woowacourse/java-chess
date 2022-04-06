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
    piece_type varchar(10) not null,
    piece_color varchar(10) not null,
    square varchar(10) not null,
    game_id int(10) not null,
    primary key (game_id, square),
    foreign key (game_id) references game (id)
);

CREATE TABLE game (
    id int(10) not null auto_increment,
    status varchar(10) not null,
    color varchar(10) not null,
    primary key (id)
);

INSERT INTO piece (type, color) VALUES
    ('pawn', 'black'),('bishop', 'black'),('rook', 'black'),('knight', 'black'), ('king', 'black'), ('queen', 'black'),
    ('pawn', 'white'),('bishop', 'white'),('rook', 'white'),('knight', 'white'), ('king', 'white'), ('queen', 'white'),
    ('empty', 'nothing');


insert into board (piece_type, piece_color, square, game_id)
VALUES ('pawn', 'white', 'a3', 11);

insert into board (piece_type, piece_color, square, game_id)
VALUES ('king', 'black', 'a4', 11);

update board set piece_type = (select a.piece_type from
    (select piece_type from board where square = ? and game_id = ?) a)
               , piece_color = (select b.piece_color from
        (select piece_color from board where square = ? and game_id = ?) b)
where square = ? and game_id = ?;

update board set piece_color = 'nothing', piece_type = 'empty' where square = ? and game_id = ?;