create table member(
  id varchar(10) not null,
  name varchar(20) not null,
  primary key(id)
);

create table role(
  id varchar(10) not null,
  role varchar(10) not null,
  primary key(id),
  foreign key(id) references member(id)
);
