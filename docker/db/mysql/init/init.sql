create table board(
    id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    name VARCHAR(100) NOT NULL,
    turn VARCHAR(10) NOT NULL
);

create table squares(
    squares_id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    board_name VARCHAR(100) NOT NULL,
    position VARCHAR(5) NOT NULL,
    piece VARCHAR(10) NOT NULL
);

