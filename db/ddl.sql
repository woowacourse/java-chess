CREATE TABLE board
(
    `id`    varchar(12) NOT NULL UNIQUE,
    `color` varchar(12) not null,
    PRIMARY KEY (`id`)
);

CREATE TABLE piece
(
    `id`          bigint      not null unique auto_increment,
    `column`      int         not null,
    `row`         int         not null,
    `piece_type`  varchar(12) not null,
    `piece_color` varchar(12) not null,
    `board_id`    varchar(12) not null,
    FOREIGN KEY (`board_id`) REFERENCES `board` (`id`),
    PRIMARY KEY (`id`)
);


CREATE TABLE player
(
    `id`       varchar(12) not null unique,
    `board_id` varchar(12) not null,
    FOREIGN KEY (`board_id`) REFERENCES `board` (`id`),
    PRIMARY KEY (`id`)
);
