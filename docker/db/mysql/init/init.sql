CREATE TABLE pieces
(
    piece_id int         not null AUTO_INCREMENT,
    position varchar(4)  not null,
    name    varchar(10) not null,
    primary key (piece_id)
);

CREATE TABLE turns
(
    turn_id  int        not null AUTO_INCREMENT,
    turn     varchar(5) not null,
    primary key (turn_id)
);