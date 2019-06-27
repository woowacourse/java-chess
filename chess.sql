show databases;
create database chess;
use chess;


create table team(
 id int auto_increment primary key,
 name varchar(10) not null
);

insert into team values (0, "BLACK");


create table chess_board(
 id int auto_increment primary key,
 status varchar(80) not null,
 team_id int not null,
 foreign key (team_id) references team (id)
 on delete no action
 on update no action
);


