create table chessGame
(
    board_id int AUTO_INCREMENT,
    turn     varchar(10) not null,
    primary key (board_id)
);

create table piece
(
    piece_id int AUTO_INCREMENT,
    board_id int,
    type     varchar(10) not null,
    team     varchar(10) not null,
    square   varchar(10) not null,
    primary key (piece_id, board_id),
    foreign key (board_id) references chessGame (board_id)
);
