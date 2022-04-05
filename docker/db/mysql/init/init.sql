create table game
(
    id   varchar(10) not null,
    turn varchar(10) not null,
    primary key (id)
);

create table board
(
    game_id varchar(10) not null,
    position varchar(10) not null,
    piece varchar(20),
    color varchar(20),
    primary key (game_id, position)
);

insert into game (id, turn) values ('1', 'WHITE');

insert into board(game_id, position, piece, color) values ('1', 'a1', 'ROOK', 'WHITE');
insert into board(game_id, position, piece, color) values ('1', 'b1', 'BISHOP', 'WHITE');
insert into board(game_id, position, piece, color) values ('1', 'c1', 'KNIGHT', 'WHITE');
insert into board(game_id, position, piece, color) values ('1', 'd1', 'KING', 'WHITE');
insert into board(game_id, position, piece, color) values ('1', 'e1', 'QUEEN', 'WHITE');
insert into board(game_id, position, piece, color) values ('1', 'f1', 'KNIGHT', 'WHITE');
insert into board(game_id, position, piece, color) values ('1', 'g1', 'BISHOP', 'WHITE');
insert into board(game_id, position, piece, color) values ('1', 'h1', 'ROOK', 'WHITE');

insert into board(game_id, position, piece, color) values ('1', 'a2', 'PAWN', 'WHITE');
insert into board(game_id, position, piece, color) values ('1', 'b2', 'PAWN', 'WHITE');
insert into board(game_id, position, piece, color) values ('1', 'c2', 'PAWN', 'WHITE');
insert into board(game_id, position, piece, color) values ('1', 'd2', 'PAWN', 'WHITE');
insert into board(game_id, position, piece, color) values ('1', 'e2', 'PAWN', 'WHITE');
insert into board(game_id, position, piece, color) values ('1', 'f2', 'PAWN', 'WHITE');
insert into board(game_id, position, piece, color) values ('1', 'g2', 'PAWN', 'WHITE');
insert into board(game_id, position, piece, color) values ('1', 'h2', 'PAWN', 'WHITE');

insert into board(game_id, position, piece, color) values ('1', 'a8', 'ROOK', 'BLACK');
insert into board(game_id, position, piece, color) values ('1', 'b8', 'BISHOP', 'BLACK');
insert into board(game_id, position, piece, color) values ('1', 'c8', 'KNIGHT', 'BLACK');
insert into board(game_id, position, piece, color) values ('1', 'd8', 'KING', 'BLACK');
insert into board(game_id, position, piece, color) values ('1', 'e8', 'QUEEN', 'BLACK');
insert into board(game_id, position, piece, color) values ('1', 'f8', 'KNIGHT', 'BLACK');
insert into board(game_id, position, piece, color) values ('1', 'g8', 'BISHOP', 'BLACK');
insert into board(game_id, position, piece, color) values ('1', 'h8', 'ROOK', 'BLACK');

insert into board(game_id, position, piece, color) values ('1', 'a7', 'PAWN', 'BLACK');
insert into board(game_id, position, piece, color) values ('1', 'b7', 'PAWN', 'BLACK');
insert into board(game_id, position, piece, color) values ('1', 'c7', 'PAWN', 'BLACK');
insert into board(game_id, position, piece, color) values ('1', 'd7', 'PAWN', 'BLACK');
insert into board(game_id, position, piece, color) values ('1', 'e7', 'PAWN', 'BLACK');
insert into board(game_id, position, piece, color) values ('1', 'f7', 'PAWN', 'BLACK');
insert into board(game_id, position, piece, color) values ('1', 'g7', 'PAWN', 'BLACK');
insert into board(game_id, position, piece, color) values ('1', 'h7', 'PAWN', 'BLACK');
