create table game(
  id int auto_increment not null,
  name varchar(20) not null,
  player varchar(6) not null,
  primary key(id)
);

create table piece(
  game_id int not null,
  position varchar(2) not null,
  type varchar(6) not null,
  player varchar(6) not null,
  primary key(game_id, position),
  foreign key(game_id) references game(id)
);
