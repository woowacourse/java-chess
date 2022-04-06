create table member
(
    id   varchar(10) not null,
    name varchar(20) not null,
    primary key (id)
);

create table role
(
    user_id varchar(10) not null,
    type    varchar(10) not null,
    primary key (user_id),
    foreign key (user_id) references member (id)
);

create table turn
(
    team char(5) not null,
    primary key (team)
);

create table board
(
    location char(2)    not null,
    team     char(5)    not null,
    name     varchar(6) not null,
    primary key (location)
);
