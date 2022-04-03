CREATE TABLE pieces
(
    piece_index int NOT NULL AUTO_INCREMENT,
    type        ENUM('k','q','b','n','r','p','.'),
    color       ENUM('BLACK','WHITE','NONE'),
    x           int NOT NULL,
    y           int NOT NULL,
    PRIMARY KEY (piece_index)
);

CREATE TABLE gameInfos
(
    state ENUM('Ready','Play','Finish'),
    turn  ENUM('BLACK','WHITE')
);
