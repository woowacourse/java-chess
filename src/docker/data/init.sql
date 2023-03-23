CREATE TABLE board
(
    `id`    INT         NOT NULL AUTO_INCREMENT,
    `turn`  VARCHAR(10) NOT NULL,
    `state` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE piece
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `board_id` INT         NOT NULL,
    `color`    VARCHAR(10) NOT NULL,
    `type`     VARCHAR(10) NOT NULL,
    `file`     VARCHAR(10) NOT NULL,
    `rank`     VARCHAR(10) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`board_id`) REFERENCES `board` (`id`)
);
