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
    constraint CHESSGAME_pk
        primary key (game_name)
);

alter table PIECE
    add game_name varchar(20) null;

alter table PIECE
    add constraint PIECE_CHESSBOARD__fk
        foreign key (game_name) references CHESSGAME (game_name)
            on update cascade on delete cascade;