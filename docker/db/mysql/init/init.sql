create table member
(
    id varchar(10) not null ,
    name varchar(20) not null,
    primary key (id)
);

create table role
(
    user_id varchar(10) not null,
    role varchar(10) not null,
    primary key (user_id),
    foreign key (user_id) references member(id)
);