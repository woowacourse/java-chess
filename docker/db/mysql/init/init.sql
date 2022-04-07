create table board (
    position varchar(2) not null,
    name varchar(20) not null,
    first boolean not null default 1,
    primary key (position)
);

insert into board (position, name) values ("a1", "rook_white");
insert into board (position, name) values ("b1", "knight_white");
insert into board (position, name) values ("c1", "bishop_white");
insert into board (position, name) values ("d1", "queen_white");
insert into board (position, name) values ("e1", "king_white");
insert into board (position, name) values ("f1", "bishop_white");
insert into board (position, name) values ("g1", "knight_white");
insert into board (position, name) values ("h1", "rook_white");

insert into board (position, name) values ("a2", "pawn_white");
insert into board (position, name) values ("b2", "pawn_white");
insert into board (position, name) values ("c2", "pawn_white");
insert into board (position, name) values ("d2", "pawn_white");
insert into board (position, name) values ("e2", "pawn_white");
insert into board (position, name) values ("f2", "pawn_white");
insert into board (position, name) values ("g2", "pawn_white");
insert into board (position, name) values ("h2", "pawn_white");

insert into board (position, name) values ("a3", "blank");
insert into board (position, name) values ("b3", "blank");
insert into board (position, name) values ("c3", "blank");
insert into board (position, name) values ("d3", "blank");
insert into board (position, name) values ("e3", "blank");
insert into board (position, name) values ("f3", "blank");
insert into board (position, name) values ("g3", "blank");
insert into board (position, name) values ("h3", "blank");

insert into board (position, name) values ("a4", "blank");
insert into board (position, name) values ("b4", "blank");
insert into board (position, name) values ("c4", "blank");
insert into board (position, name) values ("d4", "blank");
insert into board (position, name) values ("e4", "blank");
insert into board (position, name) values ("f4", "blank");
insert into board (position, name) values ("g4", "blank");
insert into board (position, name) values ("h4", "blank");

insert into board (position, name) values ("a5", "blank");
insert into board (position, name) values ("b5", "blank");
insert into board (position, name) values ("c5", "blank");
insert into board (position, name) values ("d5", "blank");
insert into board (position, name) values ("e5", "blank");
insert into board (position, name) values ("f5", "blank");
insert into board (position, name) values ("g5", "blank");
insert into board (position, name) values ("h5", "blank");

insert into board (position, name) values ("a6", "blank");
insert into board (position, name) values ("b6", "blank");
insert into board (position, name) values ("c6", "blank");
insert into board (position, name) values ("d6", "blank");
insert into board (position, name) values ("e6", "blank");
insert into board (position, name) values ("f6", "blank");
insert into board (position, name) values ("g6", "blank");
insert into board (position, name) values ("h6", "blank");

insert into board (position, name) values ("a7", "pawn_black");
insert into board (position, name) values ("b7", "pawn_black");
insert into board (position, name) values ("c7", "pawn_black");
insert into board (position, name) values ("d7", "pawn_black");
insert into board (position, name) values ("e7", "pawn_black");
insert into board (position, name) values ("f7", "pawn_black");
insert into board (position, name) values ("g7", "pawn_black");
insert into board (position, name) values ("h7", "pawn_black");

insert into board (position, name) values ("a8", "rook_black");
insert into board (position, name) values ("b8", "knight_black");
insert into board (position, name) values ("c8", "bishop_black");
insert into board (position, name) values ("d8", "queen_black");
insert into board (position, name) values ("e8", "king_black");
insert into board (position, name) values ("f8", "bishop_black");
insert into board (position, name) values ("g8", "knight_black");
insert into board (position, name) values ("h8", "rook_black");

create table game (
    state varchar(10) not null,
    primary key (state)
);

insert into game (state) values ("white_turn");
