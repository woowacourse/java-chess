CREATE TABLE board
(
    id BIGINT  NOT NULL AUTO_INCREMENT,
    a1 CHAR(1) NOT NULL DEFAULT ('r'),
    a2 CHAR(1) NOT NULL DEFAULT ('p'),
    a3 CHAR(1) NOT NULL DEFAULT ('.'),
    a4 CHAR(1) NOT NULL DEFAULT ('.'),
    a5 CHAR(1) NOT NULL DEFAULT ('.'),
    a6 CHAR(1) NOT NULL DEFAULT ('.'),
    a7 CHAR(1) NOT NULL DEFAULT ('P'),
    a8 CHAR(1) NOT NULL DEFAULT ('R'),
    b1 CHAR(1) NOT NULL DEFAULT ('n'),
    b2 CHAR(1) NOT NULL DEFAULT ('p'),
    b3 CHAR(1) NOT NULL DEFAULT ('.'),
    b4 CHAR(1) NOT NULL DEFAULT ('.'),
    b5 CHAR(1) NOT NULL DEFAULT ('.'),
    b6 CHAR(1) NOT NULL DEFAULT ('.'),
    b7 CHAR(1) NOT NULL DEFAULT ('P'),
    b8 CHAR(1) NOT NULL DEFAULT ('N'),
    c1 CHAR(1) NOT NULL DEFAULT ('b'),
    c2 CHAR(1) NOT NULL DEFAULT ('p'),
    c3 CHAR(1) NOT NULL DEFAULT ('.'),
    c4 CHAR(1) NOT NULL DEFAULT ('.'),
    c5 CHAR(1) NOT NULL DEFAULT ('.'),
    c6 CHAR(1) NOT NULL DEFAULT ('.'),
    c7 CHAR(1) NOT NULL DEFAULT ('P'),
    c8 CHAR(1) NOT NULL DEFAULT ('B'),
    d1 CHAR(1) NOT NULL DEFAULT ('q'),
    d2 CHAR(1) NOT NULL DEFAULT ('p'),
    d3 CHAR(1) NOT NULL DEFAULT ('.'),
    d4 CHAR(1) NOT NULL DEFAULT ('.'),
    d5 CHAR(1) NOT NULL DEFAULT ('.'),
    d6 CHAR(1) NOT NULL DEFAULT ('.'),
    d7 CHAR(1) NOT NULL DEFAULT ('P'),
    d8 CHAR(1) NOT NULL DEFAULT ('Q'),
    e1 CHAR(1) NOT NULL DEFAULT ('k'),
    e2 CHAR(1) NOT NULL DEFAULT ('p'),
    e3 CHAR(1) NOT NULL DEFAULT ('.'),
    e4 CHAR(1) NOT NULL DEFAULT ('.'),
    e5 CHAR(1) NOT NULL DEFAULT ('.'),
    e6 CHAR(1) NOT NULL DEFAULT ('.'),
    e7 CHAR(1) NOT NULL DEFAULT ('P'),
    e8 CHAR(1) NOT NULL DEFAULT ('K'),
    f1 CHAR(1) NOT NULL DEFAULT ('b'),
    f2 CHAR(1) NOT NULL DEFAULT ('p'),
    f3 CHAR(1) NOT NULL DEFAULT ('.'),
    f4 CHAR(1) NOT NULL DEFAULT ('.'),
    f5 CHAR(1) NOT NULL DEFAULT ('.'),
    f6 CHAR(1) NOT NULL DEFAULT ('.'),
    f7 CHAR(1) NOT NULL DEFAULT ('P'),
    f8 CHAR(1) NOT NULL DEFAULT ('B'),
    g1 CHAR(1) NOT NULL DEFAULT ('n'),
    g2 CHAR(1) NOT NULL DEFAULT ('p'),
    g3 CHAR(1) NOT NULL DEFAULT ('.'),
    g4 CHAR(1) NOT NULL DEFAULT ('.'),
    g5 CHAR(1) NOT NULL DEFAULT ('.'),
    g6 CHAR(1) NOT NULL DEFAULT ('.'),
    g7 CHAR(1) NOT NULL DEFAULT ('P'),
    g8 CHAR(1) NOT NULL DEFAULT ('N'),
    h1 CHAR(1) NOT NULL DEFAULT ('r'),
    h2 CHAR(1) NOT NULL DEFAULT ('p'),
    h3 CHAR(1) NOT NULL DEFAULT ('.'),
    h4 CHAR(1) NOT NULL DEFAULT ('.'),
    h5 CHAR(1) NOT NULL DEFAULT ('.'),
    h6 CHAR(1) NOT NULL DEFAULT ('.'),
    h7 CHAR(1) NOT NULL DEFAULT ('P'),
    h8 CHAR(1) NOT NULL DEFAULT ('R'),
    PRIMARY KEY (id)
);

CREATE TABLE move_log
(
    id              BIGINT  NOT NULL AUTO_INCREMENT,
    board_id        BIGINT  NOT NULL,
    source_position CHAR(2) NOT NULL,
    target_position CHAR(2) NOT NULL,
    source_piece    CHAR(1) NOT NULL,
    target_piece    CHAR(1) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (board_id) REFERENCES board (id)
);

CREATE TABLE chess_game
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    board_id BIGINT       NOT NULL,
    turn     VARCHAR(255) NOT NULL DEFAULT ('WHITE'),
    PRIMARY KEY (id),
    FOREIGN KEY (board_id) REFERENCES board (id)
);

CREATE TABLE player
(
    id   BIGINT       NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE chess_room
(
    id        BIGINT       NOT NULL AUTO_INCREMENT,
    game_id   BIGINT       NOT NULL,
    player_id BIGINT       NOT NULL,
    state     VARCHAR(255) NOT NULL DEFAULT "INIT",
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES chess_game (id),
    FOREIGN KEY (player_id) REFERENCES player (id)
);
