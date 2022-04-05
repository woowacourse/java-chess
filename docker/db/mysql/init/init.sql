create table piece (
    id          int not null AUTO_INCREMENT,
    name        varchar (1) not null ,
    position    varchar (2) not null ,
    imagePath   varchar (30) not null,
    primary key (id)
);

create table player (
    id      int not null AUTO_INCREMENT,
    name    varchar (10) not null,
    primary key (id)
);
