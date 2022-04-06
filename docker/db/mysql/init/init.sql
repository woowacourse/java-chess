create table Player (
    id integer not null auto_increment,
    color varchar(5) not null,
    pieces varchar(255),
    primary key (id)
);

create table Game (
    id integer not null auto_increment,
    player_id1 integer not null,
    player_id2 integer not null,
    finished boolean not null,
    turn_color varchar(5) not null,
    primary key (id)
);
