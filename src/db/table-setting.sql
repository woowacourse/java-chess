CREATE TABLE game_room
(
    id   INT         NOT NULL AUTO_INCREMENT,
    turn VARCHAR(12) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE pieces
(
    id           INT         NOT NULL AUTO_INCREMENT,
    `rank`       TINYINT UNSIGNED NOT NULL,
    file         TINYINT UNSIGNED NOT NULL,
    `type`       VARCHAR(12) NOT NULL,
    camp         VARCHAR(12) NOT NULL,
    game_room_id INT         NOT NULL,
    FOREIGN KEY (game_room_id) REFERENCES game_room (id),
    PRIMARY KEY (id)
);
