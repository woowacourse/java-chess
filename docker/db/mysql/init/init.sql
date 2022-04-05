create table Player (
    id integer not null auto_increment,
    color varchar(5) not null,
    pieces varchar(255),
    primary key (id)
);

create table GameState (
    id integer not null auto_increment,
    state varchar(20) not null,
    primary key (id)
);

create table Game (
    id integer not null auto_increment,
    state_id integer not null,
    primary key (id),
    foreign key (state_id) references GameState (id)
);

create table GameMatch (
    id integer not null auto_increment,
    game_id integer not null,
    player_id integer not null,
    primary key (id),
    foreign key (game_id) references Game (id),
    foreign key (player_id) references Player (id)
);

create table GameTurn (
    id integer not null auto_increment,
    match_id integer not null,
    current_player_id integer not null,
    primary key (id),
    foreign key (match_id) references GameMatch (id),
    foreign key (current_player_id) references GameMatch (player_id)
);