CREATE TABLE IF NOT EXISTS chess
(
    chess_id BIGINT AUTO_INCREMENT,
    turn varchar(10),
    PRIMARY KEY (chess_id)
);

CREATE TABLE IF NOT EXISTS piece
(
    piece_id BIGINT AUTO_INCREMENT,
    chess_id BIGINT,
    position varchar(100),
    color varchar(100),
    name varchar(100),
    PRIMARY KEY (piece_id),
    FOREIGN KEY (chess_id) REFERENCES chess (chess_id)
);
