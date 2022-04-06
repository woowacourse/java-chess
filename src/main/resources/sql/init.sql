create table CHESSBOARD
(
    id     bigint auto_increment primary key
);

create table PIECE
(
    id     bigint auto_increment primary key,
    type   enum ('r', 'n', 'b', 'q', 'k', 'p') not null,
    team   enum ('white', 'black')             not null,
    `rank` int                                 not null,
    file   varchar(1)                          not null
);

create table CHESSGAME
(
    game_name varchar(20) not null,
    turn      varchar(5)  not null,
    chess_board_id bigint,
    constraint CHESSGAME_pk
        primary key (game_name),
    constraint CHESSGAME_CHESSBOARD__fk
        foreign key (chess_board_id) references CHESSBOARD (id)
            on update cascade on delete cascade
);

alter table PIECE
    add chessboard_id bigint null;

alter table PIECE
    add constraint PIECE_CHESSBOARD__fk
        foreign key (chessboard_id) references CHESSBOARD (id)
            on update cascade on delete cascade;