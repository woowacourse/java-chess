create table chess.game (
	game_id int auto_increment,
	team varchar(10) not null,
	board text not null,
	primary key (game_id)
);
