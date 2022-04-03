CREATE TABLE users
(
    user_index int         NOT NULL AUTO_INCREMENT,
    user_name  varchar(20) NOT NULL,
    turn       ENUM('black','white'),
    state      ENUM('ready','play','finish'),
    PRIMARY KEY (user_index)
);

CREATE TABLE pieces
(
    piece_index int NOT NULL AUTO_INCREMENT,
    name        ENUM('king','queen','bishop','knight','rook','pawn','blank'),
    color       ENUM('black','white','none'),
    PRIMARY KEY (piece_index)
);

CREATE TABLE positions
(
    position_index int NOT NULL AUTO_INCREMENT,
    x              int NOT NULL,
    y              int NOT NULL,
    piece_index    int NOT NULL,
    user_index     int NOT NULL,
    PRIMARY KEY (position_index),
    FOREIGN KEY (piece_index) REFERENCES pieces (piece_index),
    FOREIGN KEY (user_index) REFERENCES users (user_index)
);

INSERT into pieces (name, color)
values ('king', 'black');
INSERT into pieces (name, color)
values ('king', 'white');

INSERT into pieces (name, color)
values ('queen', 'black');
INSERT into pieces (name, color)
values ('queen', 'white');

INSERT into pieces (name, color)
values ('bishop', 'black');
INSERT into pieces (name, color)
values ('bishop', 'white');

INSERT into pieces (name, color)
values ('knight', 'black');
INSERT into pieces (name, color)
values ('knight', 'white');

INSERT into pieces (name, color)
values ('rook', 'black');
INSERT into pieces (name, color)
values ('rook', 'white');

INSERT into pieces (name, color)
values ('pawn', 'black');
INSERT into pieces (name, color)
values ('pawn', 'white');

INSERT into pieces (name, color)
values ('blank', 'none');
INSERT into pieces (name, color)
values ('blank', 'none');
