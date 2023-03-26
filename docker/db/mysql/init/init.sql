create table game
(
    game_id int primary key auto_increment,
    status varchar(10)  not null,
    turn varchar(10) not null
);

create table move
(
    game_id int not null,
    start_position varchar(10) not null,
    end_position varchar(10) not null
);
