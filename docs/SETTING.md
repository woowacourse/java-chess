## 데이터베이스 환경 설정

### Docker 사용 방법

1. docker directory를 생성한다.

2. 생성한 directory 하위에 docker-compose.yml 파일 생성
```
version: "3.9"
services:
db:
image: mysql:8.0.28
platform: linux/x86_64
restart: always
ports:
- "13306:3306"
environment:
MYSQL_ROOT_PASSWORD: root
MYSQL_DATABASE: chess
MYSQL_USER: user
MYSQL_PASSWORD: password
TZ: Asia/Seoul
volumes:
- ./db/mysql/data:/var/lib/mysql
- ./db/mysql/config:/etc/mysql/conf.d
- ./db/mysql/init:/docker-entrypoint-initdb.d
```

3. docker-compose.yml 파일이 있는 경로에서, docker 명령어로 Server를 실행
```
# Docker 실행하기
docker-compose -p chess up -d
```

```
# Docker 정지하기
docker-compose -p chess down
```

### Local MYSQL 사용 방법

1. MYSQL WorkBench를 설치하고 실행한다.

2. 다음과 같이 연결 정보를 입력한다.
```
Hostname : localhost
Port : 13306
Username : root
```

3. 새로운 유저를 생성한다.
```
create user 'username'@'localhost' identified by 'password';
```

4. 생성한 유저에게 모든 db 및 테이블에 접근권한 부여
```
grant all privileges on *.* to 'username'@'localhost';
```

5. 설정한 권한 적용
```
flush privileges;
```

## 데이터베이스 생성 쿼리

1. 데이터베이스 `chess`를 만듭니다.
```
CREATE DATABASE chess DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```

2. 테스트용 데이터베이스 `chess_test`를 만듭니다.
```
CREATE DATABASE chess_test DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```

3. 각각의 데이터베이스에 다음 쿼리를 사용해 테이블을 만듭니다.

```
CREATE TABLE User (
	user_id VARCHAR(20) NOT NULL,
	nickname VARCHAR(20) NOT NULL,
	PRIMARY KEY (user_id)
)
```

```
CREATE TABLE Game (
	game_id BIGINT NOT NULL AUTO_INCREMENT,
	user_id VARCHAR(20) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (game_id),
	FOREIGN KEY (user_id) REFERENCES User(user_id)
)
```

```
CREATE TABLE Board (
	game_id BIGINT NOT NULL,
	turn INT NOT NULL,
	piece_file CHAR(1) NOT NULL,
	piece_rank CHAR(1) NOT NULL,
	piece_type VARCHAR(10) NOT NULL,
	piece_team TINYINT NOT NULL,
	PRIMARY KEY (game_id, turn, piece_file, piece_rank),
	FOREIGN KEY (game_id) REFERENCES Game(game_id)
)
```

## 👏👏👏 모든 설정을 완료했습니다!! 👏👏👏
