show databases;
create database chess;
use chess;


create table chess_board(
 id int auto_increment primary key,
 roomId int not null,
 positionX int not null,
 positionY int not null,
 unit char(1) not null
);

create table room(
 id int auto_increment primary key,
 team varchar(5) not null
 );


