CREATE TABLE game (
	game_id INT PRIMARY KEY NOT NULL,
	current_turn VARCHAR(10) DEFAULT'white'
);
CREATE TABLE piece (
	piece_id INT PRIMARY KEY AUTO_INCREMENT,
    game_id INT NOT NULL,
    piece_name VARCHAR(10) NOT NULL,
    piece_color VARCHAR(10) NOT NULL,
    position VARCHAR(2) NOT NULL,
	FOREIGN KEY (game_id) REFERENCES game(game_id)
);