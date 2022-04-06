create table board (
    position varchar(2) not null,
    name varchar(20) not null,
    primary key (position)
);

create table game (
    state varchar(10) not null,
    primary key (state)
);
