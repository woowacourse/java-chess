CREATE TABLE board (
name VARCHAR(100) NOT NULL primary key,
turn VARCHAR(10) NOT NULL
);

CREATE TABLE squares (
board_name VARCHAR(100) NOT NULL,
foreign key(board_name) references board(name),
position VARCHAR(5) NOT NULL,
piece VARCHAR(10) NOT NULL
);

