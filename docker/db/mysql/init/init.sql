create table Member
(
    id   bigint auto_increment primary key,
    name varchar(10) not null
);

create table Game
(
    id bigint auto_increment primary key,
    state varchar(15) not null,
    white_member_id bigint,
    black_member_id bigint
);

create table Piece
(
    game_id bigint not null,
    line_number tinyint,
    position_x tinyint not null,
    position_y tinyint not null,
    team varchar(10),
    type varchar(10) not null,
    first_turn bit,
    constraint fk_game_id foreign key(game_id) references Game(id)
);