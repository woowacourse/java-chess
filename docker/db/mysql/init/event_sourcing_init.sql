CREATE TABLE game
(
    id    BIGINT NOT NULL AUTO_INCREMENT,
    state ENUM('RUNNING', 'OVER') NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE event
(
    game_id  BIGINT NOT NULL,
    type  VARCHAR(20) NOT NULL,
    description VARCHAR(20)
);

-- TEST DB
CREATE TABLE game_test
(
    id    BIGINT NOT NULL AUTO_INCREMENT,
    state ENUM('RUNNING', 'OVER') NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE event_test
(
    game_id  BIGINT NOT NULL,
    type  VARCHAR(20) NOT NULL,
    description VARCHAR(20)
);
