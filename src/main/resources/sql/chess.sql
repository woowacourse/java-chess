
create user 'chess'@'localhost'identified by 'chess';
grant all privileges on *.* to 'chess'@'localhost';
flush privileges;
CREATE DATABASE chess_db DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use chess_db;
CREATE TABLE chess_info(
	turn INT NOT NULL AUTO_INCREMENT,
    row1 VARCHAR(100),
    row2 VARCHAR(100),
    row3 VARCHAR(100),
    row4 VARCHAR(100),
    row5 VARCHAR(100),
    row6 VARCHAR(100),
    row7 VARCHAR(100),
    row8 VARCHAR(100),
    PRIMARY KEY(turn)
);
SELECT * from chess_info;