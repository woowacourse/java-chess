CREATE TABLE game
(
    id     INT NOT NULL AUTO_INCREMENT UNIQUE,
    name   VARCHAR(20) NOT NULL ,
    status VARCHAR(10) NOT NULL DEFAULT 'empty',
    turn   VARCHAR(10) NOT NULL DEFAULT 'white',
    PRIMARY KEY (id)
);

CREATE TABLE board
(
    id          INT NOT NULL AUTO_INCREMENT UNIQUE,
    piece_type  VARCHAR(10) NOT NULL ,
    piece_color VARCHAR(10) NOT NULL ,
    square      VARCHAR(10) NOT NULL ,
    game_id     INT NOT NULL,
    primary key (id),
    foreign key (game_id) references game (id)
);

ALTER TABLE board ADD UNIQUE (game_id, square);

CREATE TABLE init_board
(
    square      varchar(10) not null,
    piece_type  varchar(10) not null,
    piece_color varchar(10) not null,
    primary key (square)
);

insert into init_board
(square, piece_type, piece_color)
VALUES ('a1', 'rook', 'white'),
       ('b1', 'knight', 'white'),
       ('c1', 'bishop', 'white'),
       ('d1', 'queen', 'white'),
       ('e1', 'king', 'white'),
       ('f1', 'bishop', 'white'),
       ('g1', 'knight', 'white'),
       ('h1', 'rook', 'white'),
       ('a2', 'pawn', 'white'),
       ('b2', 'pawn', 'white'),
       ('c2', 'pawn', 'white'),
       ('d2', 'pawn', 'white'),
       ('e2', 'pawn', 'white'),
       ('f2', 'pawn', 'white'),
       ('g2', 'pawn', 'white'),
       ('h2', 'pawn', 'white'),
       ('a3', 'empty', 'nothing'),
       ('b3', 'empty', 'nothing'),
       ('c3', 'empty', 'nothing'),
       ('d3', 'empty', 'nothing'),
       ('e3', 'empty', 'nothing'),
       ('f3', 'empty', 'nothing'),
       ('g3', 'empty', 'nothing'),
       ('h3', 'empty', 'nothing'),
       ('a4', 'empty', 'nothing'),
       ('b4', 'empty', 'nothing'),
       ('c4', 'empty', 'nothing'),
       ('d4', 'empty', 'nothing'),
       ('e4', 'empty', 'nothing'),
       ('f4', 'empty', 'nothing'),
       ('g4', 'empty', 'nothing'),
       ('h4', 'empty', 'nothing'),
       ('a5', 'empty', 'nothing'),
       ('b5', 'empty', 'nothing'),
       ('c5', 'empty', 'nothing'),
       ('d5', 'empty', 'nothing'),
       ('e5', 'empty', 'nothing'),
       ('f5', 'empty', 'nothing'),
       ('g5', 'empty', 'nothing'),
       ('h5', 'empty', 'nothing'),
       ('a6', 'empty', 'nothing'),
       ('b6', 'empty', 'nothing'),
       ('c6', 'empty', 'nothing'),
       ('d6', 'empty', 'nothing'),
       ('e6', 'empty', 'nothing'),
       ('f6', 'empty', 'nothing'),
       ('g6', 'empty', 'nothing'),
       ('h6', 'empty', 'nothing'),
       ('a7', 'pawn', 'black'),
       ('b7', 'pawn', 'black'),
       ('c7', 'pawn', 'black'),
       ('d7', 'pawn', 'black'),
       ('e7', 'pawn', 'black'),
       ('f7', 'pawn', 'black'),
       ('g7', 'pawn', 'black'),
       ('h7', 'pawn', 'black'),
       ('a8', 'rook', 'black'),
       ('b8', 'knight', 'black'),
       ('c8', 'bishop', 'black'),
       ('d8', 'queen', 'black'),
       ('e8', 'king', 'black'),
       ('f8', 'bishop', 'black'),
       ('g8', 'knight', 'black'),
       ('h8', 'rook', 'black');
