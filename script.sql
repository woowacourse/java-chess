CREATE TABLE chess.game (
	game_id INT auto_increment,
	team VARCHAR(10) NOT NULL,
	PRIMARY KEY (game_id)
);

CREATE TABLE chess.board (
	game_id INT,
	square_file VARCHAR(10) NOT NULL,
	square_rank VARCHAR(10) NOT NULL,
	piece_team VARCHAR(10) NOT NULL,
	piece_type VARCHAR(20) NOT NULL
);

CREATE TABLE chess.state (
    game_id INT NOT NULL,
    state varchar(10) NOT NULL
);

