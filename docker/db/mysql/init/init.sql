create table game
(
    id    int         not null AUTO_INCREMENT,
    name varchar (10) not null unique,
    command_log text not null,
    primary key (id)
);
