

create table piece (
    position varchar(10) not null,
    name varchar(20) not null,
    primary key(position)
);

create table board (
    id int(5) primary key auto_increment,
    state varchar(20) not null
);



