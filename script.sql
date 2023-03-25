create table chess.user
(
    user_id varchar(100) null
);
create table chess.chess_board
(
    user_id  varchar(100) null,
    chess_id int auto_increment,
    status   varchar(10)  null,
    constraint chess_board_pk
        primary key (chess_id)
);
create table chess.move
(
    chess_id    int         not null,
    origin      varchar(10) not null,
    destination varchar(10) not null,
    turn        int         not null
);
create table chess_test.user_dao
(
    user_id varchar(100) null,
    name    varchar(100) null
);
create table chess_test.user
(
    user_id varchar(100) null
);
create table chess_test.chess_board
(
    user_id  varchar(100) null,
    chess_id int auto_increment,
    status   varchar(10)  null,
    constraint chess_board_pk
        primary key (chess_id)
);
create table chess_test.move
(
    chess_id    int         not null,
    origin      varchar(10) not null,
    destination varchar(10) not null,
    turn        int         not null
);
