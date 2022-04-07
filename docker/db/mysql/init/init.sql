CREATE TABLE chess_game
(
    game_id    INT        NOT NULL AUTO_INCREMENT,
    game_state VARCHAR(10) NOT NULL,
    game_turn  VARCHAR(30) NOT NULL,

    PRIMARY KEY (game_id)
);

insert into chess_game (game_state, game_turn) values ("ready", "WHITE");


CREATE TABLE chess_board
(
    board_id       INT       NOT NULL AUTO_INCREMENT,
    game_id        INT        NOT NULL,
    board_position VARCHAR(10) NOT NULL,
    board_piece    VARCHAR(10) NOT NULL,
    board_color    VARCHAR(10) NOT NULL,

    PRIMARY KEY (board_id)
);

insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "a1", "r", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "a2", "p", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "a3", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "a4", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "a5", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "a6", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "a7", "p", "BLACK");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "a8", "r", "BLACK");

insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "b1", "n", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "b2", "p", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "b3", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "b4", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "b5", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "b6", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "b7", "p", "BLACK");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "b8", "n", "BLACK");

insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "c1", "b", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "c2", "p", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "c3", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "c4", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "c5", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "c6", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "c7", "p", "BLACK");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "c8", "b", "BLACK");

insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "d1", "q", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "d2", "p", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "d3", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "d4", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "d5", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "d6", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "d7", "p", "BLACK");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "d8", "q", "BLACK");

insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "e1", "k", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "e2", "p", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "e3", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "e4", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "e5", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "e6", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "e7", "p", "BLACK");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "e8", "k", "BLACK");

insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "f1", "b", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "f2", "p", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "f3", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "f4", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "f5", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "f6", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "f7", "p", "BLACK");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "f8", "b", "BLACK");

insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "g1", "n", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "g2", "p", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "g3", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "g4", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "g5", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "g6", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "g7", "p", "BLACK");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "g8", "n", "BLACK");

insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "h1", "r", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "h2", "p", "WHITE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "h3", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "h4", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "h5", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "h6", ".", "NONE");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "h7", "p", "BLACK");
insert into chess_board (game_id, board_position, board_piece, board_id) values (1, "h8", "r", "BLACK");
