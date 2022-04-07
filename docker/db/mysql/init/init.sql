create table game
(
    id        int                  not null
        primary key,
    whiteName varchar(100)         null,
    blackName varchar(100)         null,
    winner    varchar(100)         null,
    turn      tinyint(1) default 0 null
);

create table position
(
    gamdId   int         null,
    position varchar(10) null,
    piece    varchar(10) null
);

create table user
(
    id   int          not null
        primary key,
    name varchar(100) null,
    win  int          null,
    lost int          null
);

