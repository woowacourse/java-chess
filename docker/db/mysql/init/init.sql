

create table board (
    position varchar(10) not null,
    name varchar(40) not null,
    primary key(position)
);

create table chessgame (
    id int(5) primary key auto_increment,
    state varchar(20) not null
);



