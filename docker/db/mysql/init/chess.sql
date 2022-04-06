
CREATE TABLE chess_game (
    id int not null auto_increment,
    state varchar(10) not null,
    insert_datetime datetime,
    update_datetime datetime,
    primary key(id)
);

CREATE TABLE piece (
    id int not null auto_increment,
    position varchar(10) not null,
    symbol varchar(10) not null,
    chess_game_id int not null,
    primary key(id)
);
