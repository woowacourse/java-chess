CREATE TABLE game (
    `id` INT NOT NULL AUTO_INCREMENT,
    `state` VARCHAR(10) NOT NULL,
    `turn` VARCHAR(5),
    `latest_date` DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE board (
    `game_id`INT NOT NULL,
    `position` VARCHAR(2) NOT NULL,
    `type` VARCHAR(6) NOT NULL,
    `color` VARCHAR(5) NOT NULL,
    PRIMARY KEY (game_id, position),
    FOREIGN KEY (game_id) REFERENCES game (id) ON DELETE CASCADE
);

