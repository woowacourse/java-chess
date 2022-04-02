DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS piece;

CREATE TABLE board (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    turn varchar(10) NOT NULL
);

CREATE TABLE piece (
    piece_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    board_id int NOT NULL,
    team varchar(10) NOT NULL,
    coordinate varchar(10) NOT NULL,
    type varchar(10) NOT NULL,
    FOREIGN KEY (piece_id) REFERENCES board(id) ON UPDATE CASCADE
);