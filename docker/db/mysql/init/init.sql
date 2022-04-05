create table chess_game
(
    id                 int         not null,
    is_on              bool        not null,
    team_value_of_turn varchar(20) not null,
    primary key (id)
);

create table chess.board
(
    raw_position     varchar(2)  not null,
    piece_name       varchar(20) not null,
    piece_team_value varchar(20) not null,
    primary key (raw_position)
);