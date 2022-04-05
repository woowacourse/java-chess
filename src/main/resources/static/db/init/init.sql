create table room
(
    id bigint not null auto_increment,
    turn varchar(10) not null,
    primary key (id)
);

create table square
(
    id bigint not null auto_increment,
    position varchar(5) not null,
    piece varchar(20) not null,
    room_id bigint not null,
    primary key (id),
    foreign key (room_id) references room (id)
);

insert into room (turn) values ("white");


insert into square (position, piece, room_id) values ("a1", "white_rook", 1);
insert into square (position, piece, room_id) values ("b1", "white_knight", 1);
insert into square (position, piece, room_id) values ("c1", "white_bishop", 1);
insert into square (position, piece, room_id) values ("d1", "white_queen", 1);
insert into square (position, piece, room_id) values ("e1", "white_king", 1);
insert into square (position, piece, room_id) values ("f1", "white_bishop", 1);
insert into square (position, piece, room_id) values ("g1", "white_knight", 1);
insert into square (position, piece, room_id) values ("h1", "white_rook", 1);

insert into square (position, piece, room_id) values ("a2", "white_pawn", 1);
insert into square (position, piece, room_id) values ("b2", "white_pawn", 1);
insert into square (position, piece, room_id) values ("c2", "white_pawn", 1);
insert into square (position, piece, room_id) values ("d2", "white_pawn", 1);
insert into square (position, piece, room_id) values ("e2", "white_pawn", 1);
insert into square (position, piece, room_id) values ("f2", "white_pawn", 1);
insert into square (position, piece, room_id) values ("g2", "white_pawn", 1);
insert into square (position, piece, room_id) values ("h2", "white_pawn", 1);

insert into square (position, piece, room_id) values ("a8", "black_rook", 1);
insert into square (position, piece, room_id) values ("b8", "black_knight", 1);
insert into square (position, piece, room_id) values ("c8", "black_bishop", 1);
insert into square (position, piece, room_id) values ("d8", "black_queen", 1);
insert into square (position, piece, room_id) values ("e8", "black_king", 1);
insert into square (position, piece, room_id) values ("f8", "black_bishop", 1);
insert into square (position, piece, room_id) values ("g8", "black_knight", 1);
insert into square (position, piece, room_id) values ("h8", "black_rook", 1);

insert into square (position, piece, room_id) values ("a7", "black_pawn", 1);
insert into square (position, piece, room_id) values ("b7", "black_pawn", 1);
insert into square (position, piece, room_id) values ("c7", "black_pawn", 1);
insert into square (position, piece, room_id) values ("d7", "black_pawn", 1);
insert into square (position, piece, room_id) values ("e7", "black_pawn", 1);
insert into square (position, piece, room_id) values ("f7", "black_pawn", 1);
insert into square (position, piece, room_id) values ("g7", "black_pawn", 1);
insert into square (position, piece, room_id) values ("h7", "black_pawn", 1);

insert into square (position, piece, room_id) values ("a3", "empty", 1);
insert into square (position, piece, room_id) values ("b3", "empty", 1);
insert into square (position, piece, room_id) values ("c3", "empty", 1);
insert into square (position, piece, room_id) values ("d3", "empty", 1);
insert into square (position, piece, room_id) values ("e3", "empty", 1);
insert into square (position, piece, room_id) values ("f3", "empty", 1);
insert into square (position, piece, room_id) values ("g3", "empty", 1);
insert into square (position, piece, room_id) values ("h3", "empty", 1);

insert into square (position, piece, room_id) values ("a4", "empty", 1);
insert into square (position, piece, room_id) values ("b4", "empty", 1);
insert into square (position, piece, room_id) values ("c4", "empty", 1);
insert into square (position, piece, room_id) values ("d4", "empty", 1);
insert into square (position, piece, room_id) values ("e4", "empty", 1);
insert into square (position, piece, room_id) values ("f4", "empty", 1);
insert into square (position, piece, room_id) values ("g4", "empty", 1);
insert into square (position, piece, room_id) values ("h4", "empty", 1);

insert into square (position, piece, room_id) values ("a5", "empty", 1);
insert into square (position, piece, room_id) values ("b5", "empty", 1);
insert into square (position, piece, room_id) values ("c5", "empty", 1);
insert into square (position, piece, room_id) values ("d5", "empty", 1);
insert into square (position, piece, room_id) values ("e5", "empty", 1);
insert into square (position, piece, room_id) values ("f5", "empty", 1);
insert into square (position, piece, room_id) values ("g5", "empty", 1);
insert into square (position, piece, room_id) values ("h5", "empty", 1);

insert into square (position, piece, room_id) values ("a6", "empty", 1);
insert into square (position, piece, room_id) values ("b6", "empty", 1);
insert into square (position, piece, room_id) values ("c6", "empty", 1);
insert into square (position, piece, room_id) values ("d6", "empty", 1);
insert into square (position, piece, room_id) values ("e6", "empty", 1);
insert into square (position, piece, room_id) values ("f6", "empty", 1);
insert into square (position, piece, room_id) values ("g6", "empty", 1);
insert into square (position, piece, room_id) values ("h6", "empty", 1);
