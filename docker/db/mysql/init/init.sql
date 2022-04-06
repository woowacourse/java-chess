create table room (
    id      int not null AUTO_INCREMENT,
    name    varchar (30) UNIQUE not null,
    turn    varchar (10) not null,
    primary key (id)
);

create table piece (
    id          int not null AUTO_INCREMENT,
    name        varchar (1) not null ,
    position    varchar (2) not null ,
    imagePath   varchar (30) not null,
    roomId      int not null,
    primary key (id),
    foreign key (roomId) references room (id) on delete cascade
);
