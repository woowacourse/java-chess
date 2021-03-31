-- DB 생성
CREATE
DATABASE chess DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 초기 테이블 생성
CREATE TABLE user
(
    id       INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(64) NOT NULL
)

CREATE TABLE result
(
    id      INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT         NOT NULL,
    result  VARCHAR(12) NOT NULL
)

CREATE TABLE room
(
    id         INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title      VARCHAR(64) NOT NULL,
    black_user INT         NOT NULL,
    white_user INT         NOT NULL,
    status     TINYINT(4) NOT NULL
)

CREATE TABLE log
(
    id             INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    room_id        INT         NOT NULL,
    start_position VARCHAR(12) NOT NULL,
    end_position   VARCHAR(12) NOT NULL,
    register_date  timestamp DEFAULT NOW()
)

-- 초기 데이터 생성
    INSERT INTO user (nickname) VALUES
    ('blackAir'),
    ('whiteAir');


