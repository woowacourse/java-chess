# java-chess ♜

체스 미션 저장소

## 기능 구현 목록 🛠

> Console

### 프로그램 실행 방법 🏃

`ConsoleApplication`을 실행해 주세요!

---

- [x] 명령 입력
    - `start`, `end`, `status`, `move` 명령 가능
    - 게임을 시작(`start`) 하지 않으면 `move` 불가
    - 게임을 시작(`start`) 하지 않으면 `status` 불가
- [x] 체스판 초기화
    - 검은색 기물은 대문자, 흰색 기물은 소문자
- [x] 게임 종료
    - `end` 명령으로 즉시 게임 종료
    - 어느 한 쪽의 `킹`이 잡히면 게임 종료 및 점수 출력
- [x] 점수 계산
    - [기물 점수](#기물-점수)에 따라서 남아있는 기물을 합산
    - 동일한 랭크(Rank)에 같은 색의 폰이 존재하면 `0.5`점
- [x] 기물 이동
    - `move source위치 target위치`형식으로 명령해서 이동 (ex. move b2 b3)
    - 흰색 기물부터 이동
    - 번갈아가면서 기물 이동
    - 각 기물의 이동 방법은 체스 룰을 따름

> Web

### 프로그램 실행 방법 🏃

1. docker, docker-compose를 설치해주세요. (DB를 도커로 띄웁니다 🐳)
2. 프로젝트의 `docker/db/mysql` 안에 `data` 디랙토리가 있다면 삭제해주세요!

   (`data` 디랙토리가 있으면 테이블 생성 쿼리가 실행되지 않아요)
3. 로컬에서 `3306`포트가 사용중인지 체크해주세요.

   만약 `3306`포트가 사용중 이라면 `docker-compose.yml`파일에 `ports`를 변경해주세요.

    ```dockerfile
   # AS-IS
       ports:
      - "3306:3306"
   
   # TO-BE
       ports:
      - "13306:3306"
   # OR
       ports:
      - "23306:3306"
   ```


4. 터미널을 켠 후, 아래 명령어를 실행해서 DB를 도커로 띄웁니다! 🐳

    ```shell
    cd docker;
    docker-compose -p chess -up
    ```

5. `WebApplication`을 실행한 후, <a href="http://localhost:4567" target="_blank">http://localhost:4567 </a>로 접속합니다 🤗
6. 프로그램을 종료했다면 아래 명령어로 도커 컨테이너를 내려주세요 👋
    ```shell
    docker-compose -p chess down
    ```

---

| Method |           Url           |         Description         |
|--------|-------------------------|-----------------------------|
|GET     |/                        |메인 페이지                     |
|GET     |/rooms/{name}            |이름이 {name}인 방 조회          |
|POST    |/rooms/{name}            |새로운 방 생성                  |
|DELETE  |/rooms/{name}            |방 삭제                        |
|GET     |/rooms/{name}/pieces     |{name} 방이 소유한 모든 기물 조회  |
|POST    |/rooms/{name}/pieces     |{name} 방의 기물 등록           |
|PUT     |/rooms/{name}/pieces     |{name} 방의 기물 위치 변경       |
|GET     |/rooms/{name}/scores     |{name} 방의 점수 조회           |
|GET     |/rooms/{name}/turn       |{name} 방 현재 턴 조회          |
|GET     |/rooms/{name}/result     |{name} 방의 result 조회        |

## Wiki 📚

### 기물 점수

|     기물     |     점수     |
| ----------- | ----------- |
| 킹(King)     |  0         |
| 퀸(Queen)    |  9         |
| 록(Rook)     |  5         |
| 비숍(Bishop) |  3         |
| 나이트(Knight)|  2.5       |
| 폰(Pawn)     |  1         |

### 용어

- 랭크(Rank) : 체스판의 가로줄, `A ~ H` 표기
- 파일(File) : 체스판의 세로줄, `1 ~ 8` 표기

## About 폰 ♟

| 이동하려는 위치에 | 이동 가능 여부 |
|--------------|-------------|
|비어있는 칸      | 직진 (초기 위치라면 2칸까지)|
|적 기물         | 대각선       |
|아군 기물       | 이동 불가     |