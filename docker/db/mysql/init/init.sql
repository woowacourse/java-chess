create table board(
    location varchar(20) not null,
    name varchar(20) not null,
    color varchar(20) not null,
    roomID varchar(50) not null,
    foreign key (roomID) references room(id) on delete cascade,
    primary key (roomID, location)
);

create table state(
    roomID varchar(50) not null,
    color varchar(20),
    foreign key (roomID) references room(id) on delete cascade,
    primary key (roomID)
);

create table room(
    id varchar(50) not null,
    primary key(id)
);