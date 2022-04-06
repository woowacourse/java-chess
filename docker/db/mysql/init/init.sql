create table chessGame (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    turn varchar(5) NOT NULL
);
create table board (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(6) NOT NULL,
    position varchar(2) NOT NULL,
    gameId int NOT NULL,
    FOREIGN KEY (gameId) REFERENCES chessGame(id)
);