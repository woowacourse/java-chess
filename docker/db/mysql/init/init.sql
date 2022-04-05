CREATE TABLE player (
    team varchar(5) not null primary key
);

insert into player (team) values ('WHITE');
insert into player (team) values ('BLACK');

CREATE TABLE turn (
    team varchar(5) not null primary key
);

insert into turn (team) values ('WHITE');

CREATE TABLE piece (
    position varchar(3) not null primary key,
    name varchar(2) not null,
    team varchar(5) not null,
    foreign key (team) references player (team)
);

insert into piece (position, name, team) values ('a1', 'r', 'WHITE');
insert into piece (position, name, team) values ('b1', 'n', 'WHITE');
insert into piece (position, name, team) values ('c1', 'b', 'WHITE');
insert into piece (position, name, team) values ('d1', 'q', 'WHITE');
insert into piece (position, name, team) values ('e1', 'k', 'WHITE');
insert into piece (position, name, team) values ('f1', 'b', 'WHITE');
insert into piece (position, name, team) values ('g1', 'n', 'WHITE');
insert into piece (position, name, team) values ('h1', 'r', 'WHITE');

insert into piece (position, name, team) values ('a2', 'p', 'WHITE');
insert into piece (position, name, team) values ('b2', 'p', 'WHITE');
insert into piece (position, name, team) values ('c2', 'p', 'WHITE');
insert into piece (position, name, team) values ('d2', 'p', 'WHITE');
insert into piece (position, name, team) values ('e2', 'p', 'WHITE');
insert into piece (position, name, team) values ('f2', 'p', 'WHITE');
insert into piece (position, name, team) values ('g2', 'p', 'WHITE');
insert into piece (position, name, team) values ('h2', 'p', 'WHITE');

insert into piece (position, name, team) values ('a7', 'P', 'BLACK');
insert into piece (position, name, team) values ('b7', 'P', 'BLACK');
insert into piece (position, name, team) values ('c7', 'P', 'BLACK');
insert into piece (position, name, team) values ('d7', 'P', 'BLACK');
insert into piece (position, name, team) values ('e7', 'P', 'BLACK');
insert into piece (position, name, team) values ('f7', 'P', 'BLACK');
insert into piece (position, name, team) values ('g7', 'P', 'BLACK');
insert into piece (position, name, team) values ('h7', 'P', 'BLACK');

insert into piece (position, name, team) values ('a8', 'R', 'BLACK');
insert into piece (position, name, team) values ('b8', 'N', 'BLACK');
insert into piece (position, name, team) values ('c8', 'B', 'BLACK');
insert into piece (position, name, team) values ('d8', 'Q', 'BLACK');
insert into piece (position, name, team) values ('e8', 'K', 'BLACK');
insert into piece (position, name, team) values ('f8', 'B', 'BLACK');
insert into piece (position, name, team) values ('g8', 'N', 'BLACK');
insert into piece (position, name, team) values ('h8', 'R', 'BLACK');
