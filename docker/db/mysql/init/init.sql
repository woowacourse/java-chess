GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS chess DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE DATABASE IF NOT EXISTS chess_test DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

# 애플리케이션 실행용 테이블 초기화
USE chess;

CREATE TABLE IF NOT EXISTS turns
(
    current_color VARCHAR(10) NOT NULL,
    PRIMARY KEY (current_color)
);

CREATE TABLE IF NOT EXISTS pieces
(
    piece_file INT         NOT NULL,
    piece_rank INT         NOT NULL,
    piece_type VARCHAR(20) NOT NULL,
    PRIMARY KEY (piece_file, piece_rank)
);

# 테스트용 테이블 초기화
USE chess_test;

CREATE TABLE IF NOT EXISTS turns
(
    current_color VARCHAR(10) NOT NULL,
    PRIMARY KEY (current_color)
);

CREATE TABLE IF NOT EXISTS pieces
(
    piece_file INT         NOT NULL,
    piece_rank INT         NOT NULL,
    piece_type VARCHAR(20) NOT NULL,
    PRIMARY KEY (piece_file, piece_rank)
);
