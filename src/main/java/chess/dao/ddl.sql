CREATE TABLE game (
    game_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    turn VARCHAR(20)
);

CREATE TABLE board (
    game_id BIGINT,
    position VARCHAR(20),
    piece VARCHAR(20),
    color VARCHAR(20),

    PRIMARY KEY (game_id, position)
);
