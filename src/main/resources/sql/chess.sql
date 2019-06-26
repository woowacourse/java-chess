-- 유저 생성
-- CREATE USER 'whale'@'localhost' IDENTIFIED BY 'whale';
-- 생성한 유저에게 모든 db 및 테이블 접근 권한 부여
-- GRANT ALL PRIVILEGES ON *.* TO 'whale'@'localhost';
-- 설정한 권한 적용
-- FLUSH PRIVILEGES;
-- 데이터 베이스 생성 (UTF-8 설정 -> 한글 사용 가능)
-- CREATE DATABASE chess DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 해당 데이터베이스로 이동(사용)
USE chess;

DROP TABLE IF EXISTS result;
DROP TABLE IF EXISTS history;
DROP TABLE IF EXISTS round;
DROP TABLE IF EXISTS player;

CREATE TABLE player(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20)
);

CREATE TABLE round(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    white_id BIGINT NOT NULL,
    black_id BIGINT NOT NULL,
    is_end BOOLEAN DEFAULT false,
    FOREIGN KEY (white_id) REFERENCES player (id),
    FOREIGN KEY (black_id) REFERENCES player (id)
);

CREATE TABLE result(
	round_id BIGINT,
    winner BIGINT NOT NULL,
    FOREIGN KEY (round_id) REFERENCES round (id),
    FOREIGN KEY (winner) REFERENCES player (id)
);

CREATE TABLE history(
	round_id BIGINT,
    row0 VARCHAR(20),
    row1 VARCHAR(20),
    row2 VARCHAR(20),
    row3 VARCHAR(20),
    row4 VARCHAR(20),
    row5 VARCHAR(20),
    row6 VARCHAR(20),
    row7 VARCHAR(20),
    turn BIGINT NOT NULL,
    FOREIGN KEY (round_id) REFERENCES round (id)
);

INSERT INTO player(name) VALUES('김동규');	-- 1
INSERT INTO player(name) VALUES('김고래');	-- 2
INSERT INTO player(name) VALUES('whale'); 	-- 3

INSERT INTO round(white_id, black_id, is_end) VALUES(1, 2, true);
INSERT INTO round(white_id, black_id, is_end) VALUES(3, 1, false);

INSERT INTO result(round_id, winner) VALUES(1, 1);

INSERT INTO history(round_id, row0, row1, row2, row3, row4, row5, row6, row7, turn) VALUES(
	1,
	'....K...',
    '........',
    '..R.....',
    'b.....p.',
    'P.......',
    'P.....N.',
    '.r....n.',
    '....k..Q',
    1);
INSERT INTO history(round_id, row0, row1, row2, row3, row4, row5, row6, row7, turn) VALUES(
	1,
	'....K...',
    '........',
    '..R.....',
    'b.....p.',
    'P.......',
    'P.....N.',
    '.r....n.',
    '....Q...',
    2);
    
INSERT INTO history(round_id, row0, row1, row2, row3, row4, row5, row6, row7, turn) VALUES(
	2,
	'RNBKQBKR',
	'PPPPPPPP',
	'........',
	'........',
	'.....p..',
	'........',
	'ppppp.pp',
	'rnbkqbnr',
    1);

stop;

-- test
SELECT * FROM round;
SELECT * FROM player;
SELECT * FROM history;
SELECT * FROM result;

-- 아직 끝나지 않은 게임 (List<RoundInfoDTO>)
SELECT round.id, p1.name 'black_player', p2.name 'white_player', round.is_end
FROM round
INNER JOIN player p1 ON p1.id = round.white_id
INNER JOIN player p2 ON p2.id = round.black_id
WHERE round.is_end = false;

-- 라운드 별 player 정보
SELECT round.id, p1.name 'black_player', p2.name 'white_player', round.is_end
FROM round
INNER JOIN player p1 ON p1.id = round.white_id
INNER JOIN player p2 ON p2.id = round.black_id
WHERE round.id = 1;

-- history에서 마지막 turn
SELECT round_id, row0, row1, row2, row3, row4, row5, row6, row7, turn
FROM history
WHERE round_id = 1
AND turn = (SELECT MAX(turn)
			FROM history
			GROUP BY round_id
			HAVING round_id = 1);

-- 라운드 별 게임 결과
SELECT round_id, name
FROM result
INNER JOIN player ON player.id = result.winner;

-- player 등록
INSERT INTO player (name) 
  SELECT 'pobi' FROM DUAL
WHERE NOT EXISTS 
  (SELECT name FROM player WHERE name = 'pobi');

-- 이름을 사용해 round 등록
INSERT INTO round(white_id, black_id) VALUES(
(SELECT id
FROM player
WHERE name = '김동규'), 
(SELECT id
FROM player
WHERE name = '김고래'));

-- 게임 종료 시 update
UPDATE round SET is_end = true WHERE id = 1;

-- 최다 승수 ranking
SELECT name, COUNT(winner) 'numberOfWins'
FROM result
INNER JOIN player ON player.id = winner
GROUP BY winner;

SELECT *
FROM result
INNER JOIN player ON player.id = winner;