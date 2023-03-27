# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)



### 프로그램 동작 순서

1. 체스 게임 시작 문구를 출력한다
2. 사용자는 start 명령을 입력한다
3. 초기화된 체스판을 출력한다
4. 게임을 진행한다
   4-1. 사용자는 move 명령으로 체스말을 이동시킬 수 있다
   4-2. 변화된 체스판을 출력한다
5. King이 잡히면 게임을 종료한다
6. 사용자는 명령어을 입력한다
    5-1. status를 입력하면 현재 점수를 출력한다
    5-2. end를 입력하면 게임을 종료한다.

### 기능 목록 구현
- [x] 위치
    - [x] file(열)과 rank(행)를 갖는다
- [x] 방향
  - [x] 상하좌우, 대각선, 나이트의 단위 이동 방향을 갖는다
  - [x] 단위 이동 방향을 구할 수 있다
- [x] 체스말
    - [x] 팀을 갖는다
    - [x] 팀에 맞는 이름을 갖는다
    - [x] 체스말은 Rook, Knight, Bishop, King, Queen, Pawn을 갖는다
    - [x] 체스말이 없는 곳은 Empty를 갖는다
    - [x] 비숍
      - [x] 대각선으로 이동할 수 있다
    - [x] 킹
      - [x] 모든 방향으로 한 칸만 이동할 수 있다
    - [x] 나이트
      - [x] 상하좌우 한 칸 후 대각선 한 칸만 이동할 수 있다
    - [x] 폰
      - [x] 앞으로 한 칸만 이동할 수 있다
      - [x] 처음 이동 시 두 칸 이하로 이동할 수 있다
    - [x] 퀸
      - [x] 모든 방향으로 이동할 수 있다
    - [x] 룩
      - [x] 상하좌우로 이동할 수 있다
- [x] 체스 타입
  - [x] 각 체스말의 점수가 있다
- [x] 체스판
    - [x] 위치와 체스말을 갖는다
    - [x] 팀의 차례를 갖는다
    - [x] 체스말을 이동시킬 수 있다
      - [x] 출발지에 체스말이 존재하는지 확인한다
      - [x] 체스말의 이동 방향을 구한다
      - [x] 경로에 체스말이 있는지 확인한다
      - [x] 체스말이 이동할 수 있는지 확인한다
      - [x] 체스말의 위치를 변경한다
      - [x] 차례를 바꾼다
    - [x] 각 팀의 점수를 구한다
      - [x] 남아있는 체스말에 대한 점수를 구한다 
        - [x] Pawn이 한 File에 여러개 있으면 점수는 절반으로 계산한다
- [x] 체스판 청사진
    - [x] 체스판에 검은말을 채운다
    - [x] 체스판에 흰말을 채운다
    - [x] 체스판이 비어있다면 빈 객체로 채운다
- [x] 커맨드
  - [x] 커맨드 목록은 start, end, move, status가 있다 
  - [x] 커맨드가 start, end, move, status인지 확인한다
    - [x] move일 경우 `move 출발지 도착지` 형식인지 확인한다
    - [x] status일 경우 각 진영의 점수를 출력한다
- [x] 뷰
  - [x] 입력
    - [x] 사용자는 명령어를 입력한다(start, end, move, status)
  - [x] 출력
    - [x] 초기화된 체스판을 출력한다


### 4단계 기능 요구사항
- [ ] Piece 정보 : piece_type, team_color, rank, file
- [ ] Chess 정보 : turn (chessBoard id 는 추후 적용 필요)
- [ ] CRUD
  - [ ] Create
    - [ ] DB에서 불러 올 체스 게임이 없으면 만든다
  - [ ] Read
    - [ ] DB에 체스 게임 정보가 저장되어 있다면 DB에서 읽어온다
  - [ ] Update
    - [ ] 기물을 움직일 때마다 DB 업데이트 해준다
    - [ ] 우선 delete 후 save 로 구현 (UPDATE 쿼리 사용으로 변경 필요)
  - [ ] Delete
    - [ ] King 이 죽으면 DB 초기화 해준다

```sql 
CREATE TABLE chess_game (
piece_type VARCHAR(255) NOT NULL,
piece_rank TINYINT(10) NOT NULL,
piece_file VARCHAR(255) NOT NULL,
color VARCHAR(255) NOT NULL,
turn VARCHAR(255) NOT NULL
)

SELECT piece_type, piece_rank, piece_file, color, turn from chess_game;

INSERT INTO chess_game(piece_type, piece_rank, piece_file, color, turn) VALUES (?, ?, ?, ?, ?);

DELETE FROM chess_game;

TRUNCATE TABLE chess_game;
```
