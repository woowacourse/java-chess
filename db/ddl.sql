create table chess.game_room
(
    game_room_id int auto_increment
        primary key,
    status       varchar(30) not null,
    current_turn varchar(30) not null
);

create table chess.chess_board
(
    piece_type      varchar(30) not null,
    side            varchar(30) not null,
    piece_rank      varchar(30) not null,
    piece_file      varchar(30) not null,
    game_room_id_fk int         not null,
    constraint game_room_id_fk
        foreign key (game_room_id_fk) references chess.game_room (game_room_id)
);

