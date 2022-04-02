## Web 요구사항

### 메인 화면
- localhost:8081로 접속하면 메인 화면이 나온다.
- '입장하기'를 누르면체스 게임을 하기 위한 방에 입장할 수 있다.

### 게임 화면
- '새 게임 만들기'
  - 이름을 입력하면 새로운 게임을 시작한다.
    - 처음 입장하면 체스 판이 비워져 있다.
    - '시작하기'를 누르면 체스 기물이 보인다.
    - 좌표 2개를 입력해서 기물을 움직일 수 있다.
    - '나가기'를 누르면 메인 화면으로 돌아간다.
    - 기물이 움직일 때마다 점수가 업데이트 된다.
    - white와 black 중 누구 차례인지 보인다.
- '이어하기'
  - 이름을 입력하면 생성된 게임을 이어한다..

## DB 요구사항
- 게임 도중에 나가도 게임을 이어서 할 수 있다.
- 서버를 재시작해도 게임을 이어서 할 수 있다.

### 테이블
- game
  - game_id : varchar
  - name : varchar
  - state : varchar
    - START, RUNNING_WHITE, RUNNING_BLACK, FINISHED

- tile
  - tile_id : long
  - file : varchar
  - rank : varchar
  - piece : varchar
  - game_id : varchar(fk)