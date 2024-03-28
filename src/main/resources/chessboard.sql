create table chessboard
(
    board_column varchar(1) not null,
    board_row    varchar(1) not null,
    piece_type   varchar(6) not null,
    camp         varchar(5) not null,
    primary key (board_column, board_row)
);
