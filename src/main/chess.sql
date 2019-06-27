CREATE DATABASE chess DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

create table game
(
	round int not null,
	start int not null,
	target int not null
);

create unique index game_round_uindex
	on game (round);

alter table game
	add constraint game_pk
		primary key (round);
