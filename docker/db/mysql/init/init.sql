CREATE TABLE board
(
    `id`    INT         NOT NULL AUTO_INCREMENT,
    `state` VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE piece
(
    `boardId`    INT        NOT NULL,
    `coordinate` VARCHAR(10) NOT NULL,
    `symbol`     VARCHAR(10) NOT NULL,
    `team`       VARCHAR(10) NOT NULL,
    PRIMARY KEY (boardId, coordinate),
    FOREIGN KEY (boardId) REFERENCES board (id) ON DELETE CASCADE
);