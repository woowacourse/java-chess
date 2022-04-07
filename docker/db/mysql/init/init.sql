CREATE TABLE game (
                      name varchar(20) not null,
                      status varchar(10) not null,
                      turn varchar(10) not null,
                      primary key (name)
);

CREATE TABLE board (
    piece_type varchar(10) not null,
    piece_color varchar(10) not null,
    square varchar(10) not null,
    game_name varchar(20) not null,
    primary key (game_name, square),
    foreign key (game_name) references game (name)
);

insert into game (name, status, turn) VALUES ('game', 'empty', 'white')