USE chess;

CREATE TABLE IF NOT EXISTS chessboard
(
    id     int         NOT NULL AUTO_INCREMENT,
    file   VARCHAR(64) NOT NULL,
    `rank` int         NOT NULL,
    type   VARCHAR(10) NOT NULL,
    color  VARCHAR(5)  NOT NULL,
    PRIMARY KEY (id)
);
