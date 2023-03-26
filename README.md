# 도메인 명세

### Board

- [x] 체스 게임을 할 수 있는 체스판을 초기화한다.
    - [x] 말의 초기 위치는 아래와 같다.
        ```
        RNBQKBNR
        PPPPPPPP
        ........
        ........
        ........
        ........
        pppppppp
        rnbqkbnr
        ```
- [x] 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.
- [x] Piece를 움직일 수 있다.
    - [x] Knight를 제외한 Piece 이동 경로에 아무것도 없어야 한다.
    - [x] 목적지에 적이 있으면 먹을 수 있다.
    - [x] Pawn의 경우, 적 기물을 대각선으로 전진하면서 잡을 수 있다.
- [x] King이 잡혔는 지 확인할 수 있다.
- [x] 남아 있는 말에 대한 점수를 구할 수 있다.
    - [x] 같은 File에 같은 팀의 폰이 있으면 폰의 점수는 절반이 된다.

### Piece

- [x] 체스 기물을 의미한다.
- [x] 자신의 팀을 가진다.
- [x] King(K), Queen(Q), Bishop(B), Knight(N), Rook(R), Pawn(P) 각자의 이름을 가진다.
- [x] King
    - [x] 대각선, 직선으로 1칸씩만 이동할 수 있다.
- [x] Queen
    - [x] 대각선, 직선으로 무제한 이동할 수 있다.
    - [x] 기본 점수 9점
- [x] Bishop
    - [x] 대각선으로 무제한 이동할 수 있다.
    - [x] 기본 점수 3점
- [x] Rook
    - [x] 직선으로 무제한 이동할 수 있다.
    - [x] 기본 점수 5점
- [x] Knight
    - [x] 직선으로 한 칸 이동 후 대각선으로 한 칸 이동할 수 있다.
    - [x] 기물을 넘어갈 수 있다.
    - [x] 기본 점수 2.5점
- [x] Pawn
    - [x] 처음에 한 칸 또는 두 칸 이동할 수 있다.
    - [x] 처음 이동 후 한 칸 씩만 전진 가능하다.
    - [x] 기본 점수 1점

### Room

- [ ] 체스 게임 방이다.
- [ ] 방의 이름을 가진다.
- [ ] 방의 번호를 가진다.

## DB 테이블

```sql
USE
chess;

CREATE TABLE room
(
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(30) NOT NULL
);

CREATE TABLE piece
(
    piece_id      INT PRIMARY KEY AUTO_INCREMENT,
    room          INT         NOT NULL,
    name          VARCHAR(10) NOT NULL,
    team          VARCHAR(10) NOT NULL,
    position_file VARCHAR(10) NOT NULL,
    position_rank VARCHAR(10) NOT NULL,
    FOREIGN KEY (room) REFERENCES room (room_id) ON DELETE CASCADE
);
```

## ChessDao

- [ ] 모든 방을 불러올 수 있다.
- [ ] Room을 저장할 수 있다.
- [ ] Room을 제거할 수 있다.
- [ ] Board를 저장할 수 있다.
- [ ] Board를 제거할 수 있다.
- [ ] 방 번호로 Board를 불러올 수 있다.
