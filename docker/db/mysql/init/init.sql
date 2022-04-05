create table game
(
    id   varchar(10) not null,
    turn varchar(10) not null,
    primary key (id)
);

create table status
(
    game_id varchar(10) not null,
    position varchar(10) not null,
    piece varchar(20),
    primary key (game_id, position)
);

insert into game (id, turn) values ('1', 'WHITE');

insert into status(game_id, position, piece) values ('1', 'a1', 'WHITE_ROOK');
insert into status(game_id, position, piece) values ('1', 'b1', 'WHITE_BISHOP');
insert into status(game_id, position, piece) values ('1', 'c1', 'WHITE_KNIGHT');
insert into status(game_id, position, piece) values ('1', 'd1', 'WHITE_KING');
insert into status(game_id, position, piece) values ('1', 'e1', 'WHITE_QUEEN');
insert into status(game_id, position, piece) values ('1', 'f1', 'WHITE_KNIGHT');
insert into status(game_id, position, piece) values ('1', 'g1', 'WHITE_BISHOP');
insert into status(game_id, position, piece) values ('1', 'h1', 'WHITE_ROOK');

insert into status(game_id, position, piece) values ('1', 'a2', 'WHITE_PAWN');
insert into status(game_id, position, piece) values ('1', 'b2', 'WHITE_PAWN');
insert into status(game_id, position, piece) values ('1', 'c2', 'WHITE_PAWN');
insert into status(game_id, position, piece) values ('1', 'd2', 'WHITE_PAWN');
insert into status(game_id, position, piece) values ('1', 'e2', 'WHITE_PAWN');
insert into status(game_id, position, piece) values ('1', 'f2', 'WHITE_PAWN');
insert into status(game_id, position, piece) values ('1', 'g2', 'WHITE_PAWN');
insert into status(game_id, position, piece) values ('1', 'h2', 'WHITE_PAWN');

insert into status(game_id, position, piece) values ('1', 'a8', 'BLACK_ROOK');
insert into status(game_id, position, piece) values ('1', 'b8', 'BLACK_BISHOP');
insert into status(game_id, position, piece) values ('1', 'c8', 'BLACK_KNIGHT');
insert into status(game_id, position, piece) values ('1', 'd8', 'BLACK_KING');
insert into status(game_id, position, piece) values ('1', 'e8', 'BLACK_QUEEN');
insert into status(game_id, position, piece) values ('1', 'f8', 'BLACK_KNIGHT');
insert into status(game_id, position, piece) values ('1', 'g8', 'BLACK_BISHOP');
insert into status(game_id, position, piece) values ('1', 'h8', 'BLACK_ROOK');

insert into status(game_id, position, piece) values ('1', 'a7', 'BLACK_PAWN');
insert into status(game_id, position, piece) values ('1', 'b7', 'BLACK_PAWN');
insert into status(game_id, position, piece) values ('1', 'c7', 'BLACK_PAWN');
insert into status(game_id, position, piece) values ('1', 'd7', 'BLACK_PAWN');
insert into status(game_id, position, piece) values ('1', 'e7', 'BLACK_PAWN');
insert into status(game_id, position, piece) values ('1', 'f7', 'BLACK_PAWN');
insert into status(game_id, position, piece) values ('1', 'g7', 'BLACK_PAWN');
insert into status(game_id, position, piece) values ('1', 'h7', 'BLACK_PAWN');
