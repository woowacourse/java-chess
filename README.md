# 체스 미션

## 구현할 기능 목록

- [x] 'start'를 입력받아 새로운 체스 게임을 시작한다.

- [x] 체스 게임이 시작되면 체스판을 초기화한다.
    - 각 행은 아래부터 1~8행으로 구성된다.
    - 각 열은 좌측부터 a~h열로 구성된다.
    - 백색 진영은 아래쪽에, 흑색 진영은 위쪽에 위치한다.
    - 2행과 7행은 각각 8개의 폰(Pawn)들로 구성된다.
    - 1행과 8행은 각각 2개의 룩(Rook), 2개의 나이트(Knight), 2개의 비숍(Bishop), 퀸(Queen)과 킹(King)으로 구성된다.

- [ ] 체스 게임이 진행되는 동안 `move source위치 target위치` 형식으로 입력받아 체스 말을 이동시킨다.
    - [ ] `move source위치`에 해당되는 체스 말이 없는 경우 예외가 발생한다.
    - [ ] `target위치`에 같은 색의 체스 말이 존재하는 경우 예외가 발생한다.
    - [ ] 체스 말의 이동 가능 범위에 `target위치`가 포함되어 있지 않은 경우 예외가 발생한다.

- [ ] `target위치`에 다른 색의 체스 말이 존재하는 경우 해당 말을 잡을 수 있다.
    - [ ] 체스 말의 공격 가능 범위에 상대방 체스 말이 해당되지 않는 경우 예외가 발생한다.
    - [ ] 해당 위치에 같은 색의 체스 말이 존재하는 경우 예외가 발생한다.

- [x] 'end'를 입력받아 프로그램을 종료한다.

### 체스판 초기화

```
♖♘♙♕♔♙♘♖ 8 (rank 8)
♗♗♗♗♗♗♗♗ 7
........ 6
........ 5
........ 4
........ 3
♝♝♝♝♝♝♝♝ 2
♜♞♟♛♚♟♞♜ 1 (rank 1)

abcdefgh (files)
```
