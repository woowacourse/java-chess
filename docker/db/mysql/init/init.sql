CREATE TABLE game (
    `id` INT NOT NULL AUTO_INCREMENT,
    `state` VARCHAR(10) NOT NULL,
    `latestDate` DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE board (
    `id` INT NOT NULL AUTO_INCREMENT,
    `game_id`INT NOT NULL,
    `position` VARCHAR(2) NOT NULL,
    `type` VARCHAR(6) NOT NULL,
    `color` VARCHAR(5) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES game (id) ON DELETE CASCADE
);

