create table player
(
    id   int auto_increment primary key,
    name varchar(255) not null,
    constraint name unique (name)
);
