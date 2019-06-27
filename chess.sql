create database chess default character set utf8 collate utf8_general_ci;
	
show databases;
use chess;
	
CREATE TABLE chess_player (
	row_num TINYINT UNSIGNED NOT NULL,
	column_num TINYINT UNSIGNED NOT NULL,
	chess_piece VARCHAR(11) NOT NULL,
	is_white_team BIT NOT NULL,
	round INTEGER(11) UNSIGNED,
	PRIMARY KEY (row_num, column_num, round)
);
	
CREATE TABLE chess_turn (
	round INTEGER(11) UNSIGNED PRIMARY KEY,
	is_white_turn BIT 
);
    
    
    
    
    
    
    
    
    
SET SQL_SAFE_UPDATES = 0;
delete from chess_player where is_white_team = true;
delete from chess_turn;
SET SQL_SAFE_UPDATES = 1;
	