CREATE TABLE move_position
(
    seq        INT NOT NULL AUTO_INCREMENT,
    romMoveFile varchar(20) ,
    fromMoveRank varchar(20),
    toMoveFile varchar(20) ,
    toMoveRank varchar(20) ,
    PRIMARY KEY(seq)
) ENGINE=MYISAM CHARSET=utf8;

