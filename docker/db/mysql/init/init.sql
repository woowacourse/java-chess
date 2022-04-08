create table chess.chess_game
(
    chess_game_id      int primary key auto_increment,
    name               varchar(20) not null unique,
    is_on              bool        not null,
    team_value_of_turn varchar(20) not null
);

create table chess.board
(
    board_id              int primary key auto_increment,
    name                  varchar(20) not null,
    position_column_value varchar(1)  not null,
    position_row_value    int         not null,
    piece_name            varchar(20) not null,
    piece_team_value      varchar(20) not null
)