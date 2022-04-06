CREATE TABLE board
(
    board_id int        not null AUTO_INCREMENT,
    position varchar(4) not null,
    piece    varchar(10) not null,
    primary key (board_id)
);