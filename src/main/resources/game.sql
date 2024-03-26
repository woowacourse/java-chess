create table game
(
    id           int auto_increment primary key,
    current_team varchar(5) not null,
    status       varchar(7) not null
);
