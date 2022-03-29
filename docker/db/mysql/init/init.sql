create table member
(
    id   varchar(10) not null,
    name varchar(20) not null,
    primary key (id)
);

create table role(
    member_id varchar(10) not null,
    role varchar(10) not null,
    primary key (member_id),
    foreign key (member_id) references member (id)
);