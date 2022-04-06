--create table member(
--  id varchar(10) not null,
--  name varchar(20) not null,
--  primary key(id)
--);
--
--create table role(
--  id varchar(10) not null,
--  role varchar(10) not null,
--  primary key(id),
--  foreign key(id) references member(id)
--);

create table game(
  name varchar(20) not null,
  player varchar(6) not null,
  primary key(name)
);

create table piece(
  game_name varchar(20) not null,
  position varchar(2) not null,
  type varchar(6) not null,
  player varchar(6) not null,
  primary key(game_name, position),
  foreign key(game_name) references game(name)
);
