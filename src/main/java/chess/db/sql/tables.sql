USE chess;

CREATE TABLE board
(
    id              INT        NOT NULL AUTO_INCREMENT,
    position_row    TINYINT    NOT NULL,
    position_column TINYINT    NOT NULL,
    team            CHAR(5)    NOT NULL,
    kind            VARCHAR(6) NOT NULL,
    is_moved        BOOLEAN    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE turn
(
    team CHAR(5) NOT NULL
);
