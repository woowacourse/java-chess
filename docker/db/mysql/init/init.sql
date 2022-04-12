create table board (
    id int not null AUTO_INCREMENT PRIMARY KEY,
    position varchar(3) not null,
    piece varchar(6),
    color varchar(5)
);

create table state (
    game_state varchar(7) not null,
    color varchar(5) not null
);

insert into state (game_state, color) value ("Waiting", "NONE");
