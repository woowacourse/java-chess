-- user
CREATE TABLE user
(
    user_id   BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_name varchar(255) UNIQUE NOT NULL,
    PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- chess_game
CREATE TABLE chess_game
(
    chess_game_id BIGINT(20) NOT NULL AUTO_INCREMENT,
    current_camp  VARCHAR(5) NOT NULL,
    user_id       BIGINT(20) NOT NULL,
    PRIMARY KEY (chess_game_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- piece
CREATE TABLE piece
(
    piece_id      BIGINT(20) NOT NULL AUTO_INCREMENT,
    piece_rank    INT         NOT NULL,
    piece_file    INT         NOT NULL,
    piece_type    VARCHAR(10) NOT NULL,
    camp          VARCHAR(5)  NOT NULL,
    chess_game_id BIGINT(20) NOT NULL,
    PRIMARY KEY (piece_id),
    FOREIGN KEY (chess_game_id) REFERENCES chess_game (chess_game_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

