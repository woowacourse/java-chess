CREATE TABLE game (
    `id` varchar(100) NOT NULL,
    `state` VARCHAR(10) NOT NULL,
    `turn` VARCHAR(5),
    PRIMARY KEY (id)
);

CREATE TABLE board (
    `game_id` varchar(100) NOT NULL,
    `position` VARCHAR(2) NOT NULL,
    `type` VARCHAR(6) NOT NULL,
    `color` VARCHAR(5) NOT NULL,
    PRIMARY KEY (game_id, position),
    FOREIGN KEY (game_id) REFERENCES game (id) ON DELETE CASCADE
);

INSERT INTO game VALUES ("First Game", "Started", "white");

INSERT INTO board VALUES ("First Game", "a1", "pawn", "white");
INSERT INTO board VALUES ("First Game", "a2", "rook", "black");
INSERT INTO board VALUES ("First Game", "c2", "pawn", "white");
