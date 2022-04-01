CREATE TABLE member (
    id varchar(10) NOT NULL,
    name varchar(20) NOT NULL,
    primary key (id)
);

CREATE TABLE role (
    user_id varchar(10) NOT NULL,
    role varchar(10) NOT NULL,
    primary key (user_id),
    foreign key (user_id) references member (id)
);