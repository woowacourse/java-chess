
CREATE TABLE board (
   position VARCHAR(5) NOT NULL,
   piece VARCHAR(20) NOT NULL,
   PRIMARY KEY (position)
);

insert into board (position, piece) values ("a1", "white rook");
insert into board (position, piece) values ("b1", "white knight");
insert into board (position, piece) values ("c1", "white bishop");
insert into board (position, piece) values ("d1", "white queen");
insert into board (position, piece) values ("e1", "white king");
insert into board (position, piece) values ("f1", "white bishop");
insert into board (position, piece) values ("g1", "white knight");
insert into board (position, piece) values ("h1", "white rook");

insert into board (position, piece) values ("a2", "white pawn");
insert into board (position, piece) values ("b2", "white pawn");
insert into board (position, piece) values ("c2", "white pawn");
insert into board (position, piece) values ("d2", "white pawn");
insert into board (position, piece) values ("e2", "white pawn");
insert into board (position, piece) values ("f2", "white pawn");
insert into board (position, piece) values ("g2", "white pawn");
insert into board (position, piece) values ("h2", "white pawn");

insert into board (position, piece) values ("a8", "black rook");
insert into board (position, piece) values ("b8", "black knight");
insert into board (position, piece) values ("c8", "black bishop");
insert into board (position, piece) values ("d8", "black queen");
insert into board (position, piece) values ("e8", "black king");
insert into board (position, piece) values ("f8", "black bishop");
insert into board (position, piece) values ("g8", "black knight");
insert into board (position, piece) values ("h8", "black rook");

insert into board (position, piece) values ("a7", "black pawn");
insert into board (position, piece) values ("b7", "black pawn");
insert into board (position, piece) values ("c7", "black pawn");
insert into board (position, piece) values ("d7", "black pawn");
insert into board (position, piece) values ("e7", "black pawn");
insert into board (position, piece) values ("f7", "black pawn");
insert into board (position, piece) values ("g7", "black pawn");
insert into board (position, piece) values ("h7", "black pawn");

insert into board (position, piece) values ("a3", " ");
insert into board (position, piece) values ("b3", " ");
insert into board (position, piece) values ("c3", " ");
insert into board (position, piece) values ("d3", " ");
insert into board (position, piece) values ("e3", " ");
insert into board (position, piece) values ("f3", " ");
insert into board (position, piece) values ("g3", " ");
insert into board (position, piece) values ("h3", " ");

insert into board (position, piece) values ("a4", " ");
insert into board (position, piece) values ("b4", " ");
insert into board (position, piece) values ("c4", " ");
insert into board (position, piece) values ("d4", " ");
insert into board (position, piece) values ("e4", " ");
insert into board (position, piece) values ("f4", " ");
insert into board (position, piece) values ("g4", " ");
insert into board (position, piece) values ("h4", " ");

insert into board (position, piece) values ("a5", " ");
insert into board (position, piece) values ("b5", " ");
insert into board (position, piece) values ("c5", " ");
insert into board (position, piece) values ("d5", " ");
insert into board (position, piece) values ("e5", " ");
insert into board (position, piece) values ("f5", " ");
insert into board (position, piece) values ("g5", " ");
insert into board (position, piece) values ("h5", " ");

insert into board (position, piece) values ("a6", " ");
insert into board (position, piece) values ("b6", " ");
insert into board (position, piece) values ("c6", " ");
insert into board (position, piece) values ("d6", " ");
insert into board (position, piece) values ("e6", " ");
insert into board (position, piece) values ("f6", " ");
insert into board (position, piece) values ("g6", " ");
insert into board (position, piece) values ("h6", " ");



CREATE TABLE turn (
   team varchar(10) not null,
   primary key (team)
);

insert into turn (team) value ("white");