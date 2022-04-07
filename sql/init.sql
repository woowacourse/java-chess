create table board (
    id int not null AUTO_INCREMENT PRIMARY KEY,
    position varchar(3) not null,
    piece varchar(6),
    color varchar(5)
);

create table state (
    color varchar(5) not null
);