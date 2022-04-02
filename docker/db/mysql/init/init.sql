DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS piece;

CREATE TABLE board
(
    id   INT(10) not null AUTO_INCREMENT ,
    turn VARCHAR (5) not null,
    primary key (id)
);

INSERT INTO board (turn) VALUES ("white");

CREATE TABLE piece
(
    id       INT(10) not null AUTO_INCREMENT,
    board_id INT(10),
    position CHAR(2),
    type     VARCHAR (20) not null,
    team     VARCHAR (10) not null,
    foreign key (board_id) references board (id) ON DELETE CASCADE ,
    primary key (id)
);

INSERT INTO piece (position, board_id, type, team) VALUES ("a1", 1, "rook", "white");
INSERT INTO piece (position, board_id, type, team) VALUES ("b1", 1, "knight", "white");
INSERT INTO piece (position, board_id, type, team) VALUES ("c1", 1, "bishop", "white");
INSERT INTO piece (position, board_id, type, team) VALUES ("d1", 1, "queen", "white");
INSERT INTO piece (position, board_id, type, team) VALUES ("e1", 1, "king", "white");
INSERT INTO piece (position, board_id, type, team) VALUES ("f1", 1, "bishop", "white");
INSERT INTO piece (position, board_id, type, team) VALUES ("g1", 1, "knight", "white");
INSERT INTO piece (position, board_id, type, team) VALUES ("h1", 1, "rook", "white");

INSERT INTO piece (position, board_id, type, team) VALUES ("a2", 1, "pawn", "white");
INSERT INTO piece (position, board_id, type, team) VALUES ("b2", 1, "pawn", "white");
INSERT INTO piece (position, board_id, type, team) VALUES ("c2", 1, "pawn", "white");
INSERT INTO piece (position, board_id, type, team) VALUES ("d2", 1, "pawn", "white");
INSERT INTO piece (position, board_id, type, team) VALUES ("e2", 1, "pawn", "white");
INSERT INTO piece (position, board_id, type, team) VALUES ("f2", 1, "pawn", "white");
INSERT INTO piece (position, board_id, type, team) VALUES ("g2", 1, "pawn", "white");
INSERT INTO piece (position, board_id, type, team) VALUES ("h2", 1, "pawn", "white");

INSERT INTO piece (position, board_id, type, team) VALUES ("a3", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("b3", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("c3", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("d3", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("e3", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("f3", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("g3", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("h3", 1, "empty", "none");

INSERT INTO piece (position, board_id, type, team) VALUES ("a4", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("b4", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("c4", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("d4", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("e4", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("f4", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("g4", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("h4", 1, "empty", "none");

INSERT INTO piece (position, board_id, type, team) VALUES ("a5", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("b5", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("c5", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("d5", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("e5", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("f5", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("g5", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("h5", 1, "empty", "none");

INSERT INTO piece (position, board_id, type, team) VALUES ("a6", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("b6", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("c6", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("d6", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("e6", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("f6", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("g6", 1, "empty", "none");
INSERT INTO piece (position, board_id, type, team) VALUES ("h6", 1, "empty", "none");

INSERT INTO piece (position, board_id, type, team) VALUES ("a7", 1, "pawn", "black");
INSERT INTO piece (position, board_id, type, team) VALUES ("b7", 1, "pawn", "black");
INSERT INTO piece (position, board_id, type, team) VALUES ("c7", 1, "pawn", "black");
INSERT INTO piece (position, board_id, type, team) VALUES ("d7", 1, "pawn", "black");
INSERT INTO piece (position, board_id, type, team) VALUES ("e7", 1, "pawn", "black");
INSERT INTO piece (position, board_id, type, team) VALUES ("f7", 1, "pawn", "black");
INSERT INTO piece (position, board_id, type, team) VALUES ("g7", 1, "pawn", "black");
INSERT INTO piece (position, board_id, type, team) VALUES ("h7", 1, "pawn", "black");

INSERT INTO piece (position, board_id, type, team) VALUES ("a8", 1, "rook", "black");
INSERT INTO piece (position, board_id, type, team) VALUES ("b8", 1, "knight", "black");
INSERT INTO piece (position, board_id, type, team) VALUES ("c8", 1, "bishop", "black");
INSERT INTO piece (position, board_id, type, team) VALUES ("d8", 1, "queen", "black");
INSERT INTO piece (position, board_id, type, team) VALUES ("e8", 1, "king", "black");
INSERT INTO piece (position, board_id, type, team) VALUES ("f8", 1, "bishop", "black");
INSERT INTO piece (position, board_id, type, team) VALUES ("g8", 1, "knight", "black");
INSERT INTO piece (position, board_id, type, team) VALUES ("h8", 1, "rook", "black");

