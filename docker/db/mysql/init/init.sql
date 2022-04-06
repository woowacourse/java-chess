CREATE table member
(
    id   varchar(10) not null,
    name varchar(20) not null,
    primary key (id)
);

create table role
(
    user_id varchar(10) not null,
    role varchar(10) not null,
    primary key (user_id),
    foreign key (user_id) references member (id)
);

create table board
(
    id int not null auto_increment primary key,
    type varchar(1) not null,
    y int not null,
    x int not null
);
