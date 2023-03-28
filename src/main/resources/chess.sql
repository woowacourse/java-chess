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
    user_id  int
);

CREATE TABLE Move
(
    id      int PRIMARY KEY AUTO_INCREMENT,
    source  varchar(2),
    target  varchar(2),
    room_id int
);

ALTER TABLE Room
    ADD FOREIGN KEY (user_id) REFERENCES User (`id`);

ALTER TABLE Move
    ADD FOREIGN KEY (room_id) REFERENCES Room (`id`);
