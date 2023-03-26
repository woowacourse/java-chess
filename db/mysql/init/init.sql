CREATE TABLE game
(
    id     INT          NOT NULL AUTO_INCREMENT COMMENT '방 번호',
    status BOOLEAN      NOT NULL COMMENT '게임 상태(진행중, 종료)',
    color  VARCHAR(128) NOT NULL COMMENT '현재 턴',
    PRIMARY KEY (id)
);

CREATE TABLE board
(
    id         INT          NOT NULL COMMENT '방 번호',
    position   VARCHAR(128) NOT NULL COMMENT '기물 위치',
    color      VARCHAR(128) NOT NULL COMMENT '기물 색',
    piece_type VARCHAR(128) NOT NULL COMMENT '기물 타입',
    FOREIGN KEY (id) REFERENCES game (id),
    PRIMARY KEY (id, position)
);
