CREATE TABLE game
(
    id    bigint NOT NULL AUTO_INCREMENT,
    state ENUM('RUNNING', 'OVER') NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE event
(
    game_id  bigint NOT NULL,
    type  ENUM('MOVE', 'CASTLING', 'PROMOTION') NOT NULL,
    description varchar(20)
);

-- TEST DB
CREATE TABLE game_test
(
    id    bigint NOT NULL AUTO_INCREMENT,
    state ENUM('RUNNING', 'OVER') NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE event_test
(
    game_id  bigint NOT NULL,
    type  ENUM('MOVE', 'CASTLING', 'PROMOTION') NOT NULL,
    description varchar(20)
);
