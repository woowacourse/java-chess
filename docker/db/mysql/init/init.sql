create table game
(
    id    int         not null AUTO_INCREMENT,
    name varchar (10) not null,
    command_log varchar(10000) not null,
    primary key (id)
);
