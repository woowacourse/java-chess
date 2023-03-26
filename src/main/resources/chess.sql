create
database testchess;

CREATE TABLE User
(
    id   int PRIMARY KEY AUTO_INCREMENT,
    name varchar(255)
);

CREATE TABLE Room
(
    id       int PRIMARY KEY AUTO_INCREMENT,
    name     varchar(255),
    finished boolean,
    user_id  int
);

CREATE TABLE Move
(
    id      int PRIMARY KEY AUTO_INCREMENT,
    source  varchar(2),
    target  varchar(2),
    room_id int
);
