/**
  최초 실행할 때 한번 import 해주세요!
 */

create table board
(
    position varchar(10) not null,
    piece    varchar(20) not null,
    primary key (position)
);

create table turn
(
    team varchar(10) not null,
    primary key (team)
);


create table game_status
(
    status varchar(10) not null,
    primary key (status)
);



insert into board (position, piece) values ("a1", "white_rook");
insert into board (position, piece) values ("b1", "white_knight");
insert into board (position, piece) values ("c1", "white_bishop");
insert into board (position, piece) values ("d1", "white_queen");
insert into board (position, piece) values ("e1", "white_king");
insert into board (position, piece) values ("f1", "white_bishop");
insert into board (position, piece) values ("g1", "white_knight");
insert into board (position, piece) values ("h1", "white_rook");

insert into board (position, piece) values ("a2", "white_pawn");
insert into board (position, piece) values ("b2", "white_pawn");
insert into board (position, piece) values ("c2", "white_pawn");
insert into board (position, piece) values ("d2", "white_pawn");
insert into board (position, piece) values ("e2", "white_pawn");
insert into board (position, piece) values ("f2", "white_pawn");
insert into board (position, piece) values ("g2", "white_pawn");
insert into board (position, piece) values ("h2", "white_pawn");

insert into board (position, piece) values ("a8", "black_rook");
insert into board (position, piece) values ("b8", "black_knight");
insert into board (position, piece) values ("c8", "black_bishop");
insert into board (position, piece) values ("d8", "black_queen");
insert into board (position, piece) values ("e8", "black_king");
insert into board (position, piece) values ("f8", "black_bishop");
insert into board (position, piece) values ("g8", "black_knight");
insert into board (position, piece) values ("h8", "black_rook");

insert into board (position, piece) values ("a7", "black_pawn");
insert into board (position, piece) values ("b7", "black_pawn");
insert into board (position, piece) values ("c7", "black_pawn");
insert into board (position, piece) values ("d7", "black_pawn");
insert into board (position, piece) values ("e7", "black_pawn");
insert into board (position, piece) values ("f7", "black_pawn");
insert into board (position, piece) values ("g7", "black_pawn");
insert into board (position, piece) values ("h7", "black_pawn");

insert into board (position, piece) values ("a3", "blank");
insert into board (position, piece) values ("b3", "blank");
insert into board (position, piece) values ("c3", "blank");
insert into board (position, piece) values ("d3", "blank");
insert into board (position, piece) values ("e3", "blank");
insert into board (position, piece) values ("f3", "blank");
insert into board (position, piece) values ("g3", "blank");
insert into board (position, piece) values ("h3", "blank");

insert into board (position, piece) values ("a4", "blank");
insert into board (position, piece) values ("b4", "blank");
insert into board (position, piece) values ("c4", "blank");
insert into board (position, piece) values ("d4", "blank");
insert into board (position, piece) values ("e4", "blank");
insert into board (position, piece) values ("f4", "blank");
insert into board (position, piece) values ("g4", "blank");
insert into board (position, piece) values ("h4", "blank");

insert into board (position, piece) values ("a5", "blank");
insert into board (position, piece) values ("b5", "blank");
insert into board (position, piece) values ("c5", "blank");
insert into board (position, piece) values ("d5", "blank");
insert into board (position, piece) values ("e5", "blank");
insert into board (position, piece) values ("f5", "blank");
insert into board (position, piece) values ("g5", "blank");
insert into board (position, piece) values ("h5", "blank");

insert into board (position, piece) values ("a6", "blank");
insert into board (position, piece) values ("b6", "blank");
insert into board (position, piece) values ("c6", "blank");
insert into board (position, piece) values ("d6", "blank");
insert into board (position, piece) values ("e6", "blank");
insert into board (position, piece) values ("f6", "blank");
insert into board (position, piece) values ("g6", "blank");
insert into board (position, piece) values ("h6", "blank");



insert into turn (team) value ("white");

insert into game_status (status) value ("READY");