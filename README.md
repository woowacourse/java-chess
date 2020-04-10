# java-chess
체스 게임 구현을 위한 저장소

## 기능 목록

### 체스판 초기화

- 게임 시작 명령어를 입력받는다.
    - start 또는 end로 입력한다.
- 체스판을 초기화한다.
    - 가로 위치는 왼쪽부터 a~h이고, 세로는 아래부터 위로 1~8이다.	

### 말 이동

- 말이 이동할 위치를 입력받는다.
    - `move source위치 target위치`를 실행해 이동한다.
    - 예외
        - source 위치에 말이 존재하지 않는 경우
        - source 위치에 있는 말이 target으로 이동할 수 없는 경우
- 이동 후의 체스판의 상황을 출력한다.


## 게임 실행법

1. Docker 로 mysql 서버 실행하기 (docker 필요)
- 터미널 : src/main/resources/sql 로 이동
- 터미널 : docker-compose up -d; 명령어로 서버시작

2. DB 세팅하기 (쿼리문 작성)
- 터미널 : docker exec -it local-chess-db bash;
- 터미널 : mysql -u root -p; (비밀번호 : root) 로 mysql 접속
- src/main/resources/sql 내의 sql 문 보고 쿼리문 작성.

3. 실행
- WebUIApplication main 실행
- localhost:4567 로 접속