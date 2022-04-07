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
