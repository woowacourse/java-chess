create table board (
    position varchar(2) not null,
    name varchar(20) not null,
    first boolean not null default 1,
    primary key (position)
);

create table game (
    state varchar(10) not null,
    primary key (state)
);
