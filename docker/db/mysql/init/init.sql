create table Game (
    game_id int auto_increment primary key,
    turn varchar(10) not null
);

create table Piece (
    piece_id int auto_increment primary key,
    piece_type varchar(10) not null,
    position_column varchar(1) not null,
    position_row int not null,
    team varchar(10) not null,
    game_id int not null,
    foreign key (game_id) references Game (game_id)
)
