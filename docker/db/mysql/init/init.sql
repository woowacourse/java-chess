CREATE TABLE game
(
    id    bigint not null,
    state ENUM('WHITE_TURN', 'BLACK_TURN', 'OVER') not null,
    PRIMARY KEY (id)
);

CREATE TABLE piece
(
    game_id  bigint     not null,
    position varchar(2) not null,
    type     ENUM('PAWN', 'KNIGHT', 'BISHOP', 'ROOK', 'QUEEN', 'KING') not null,
    color    ENUM('WHITE', 'BLACK') not null,
    primary key (game_id, position),
    foreign key (game_id) references game (id)
);

CREATE TABLE result
(
    game_id     bigint not null,
    winner      ENUM('WHITE', 'BLACK') not null,
    white_score float  not null,
    black_score float  not null,
    primary key (game_id),
    foreign key (game_id) references game (id)
);

-- TEST DB
CREATE TABLE game_test
(
    id    bigint not null,
    state ENUM('WHITE_TURN', 'BLACK_TURN', 'OVER') not null,
    PRIMARY KEY (id)
);

CREATE TABLE piece_test
(
    game_id  bigint     not null,
    position varchar(2) not null,
    type     ENUM('PAWN', 'KNIGHT', 'BISHOP', 'ROOK', 'QUEEN', 'KING') not null,
    color    ENUM('WHITE', 'BLACK') not null,
    primary key (game_id, position),
    foreign key (game_id) references game (id)
);

CREATE TABLE result_test
(
    game_id     bigint not null,
    winner      ENUM('WHITE', 'BLACK') not null,
    white_score float  not null,
    black_score float  not null,
    primary key (game_id),
    foreign key (game_id) references game (id)
);
