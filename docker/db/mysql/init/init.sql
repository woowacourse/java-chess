-- create table member (
--     id varchar(10) not null,
--     name varchar(20) not null,
--     primary key (id)
-- );

create table piece (
    position varchar(2) not null,
    color varchar(5) not null,
    role varchar(1) not null,
    primary key (position)
);
-- ALTER TABLE table_name MODIFY COLUMN ex_column varchar(16) NULL;
create table board (
    id integer not null,
    turn varchar(5) not null
);