create table chess.chess_game
(
    id                 int primary key auto_increment,
    name               varchar(20) not null,
    is_on              bool        not null,
    team_value_of_turn varchar(20) not null
);

create table chess.board
(
    id               int primary key auto_increment,
    name             varchar(20) not null,
    raw_position     varchar(2)  not null,
    piece_name       varchar(20) not null,
    piece_team_value varchar(20) not null
);