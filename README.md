# 기능목록

## 1단계

### 도메인

#### state

- InitialPawnState
    - [x] 해당 위치에 아무 말이 없다면 앞으로 한 칸 혹은 두 칸을 갈 수 있다.
    - [x] 해당 위치에 상대편 말이 있다면 대각 방향으로 한 칸 이동할 수 있다.

- MovedPawnState
    - [x] 해당 위치에 아무 말이 없다면 앞으로 한 칸 갈 수 있다.
    - [x] 해당 위치에 상대편 말이 있다면 대각 방향으로 한 칸 이동할 수 있다.

- KnightState
    - [x] 해당 위치에 같은 색의 기물이 없으면 앞으로 한 칸 이동한 후 같은 방향 대각으로 한 칸 이동할 수 있다.
    - [x] 다른 piece 가 중간에 있더라도 뛰어넘을 수 있다

- BishopState
    - [x] 해당 위치에 같은 색의 기물이 없으면 대각 방향으로 원하는 만큼 이동할 수 있다.

- RookState
    - [x] 해당 위치에 같은 색의 기물이 없으면 직선 방향으로 원하는 만큼 이동할 수 있다.

- QueenState
    - [x] 해당 위치에 같은 색의 기물이 없으면 직선 방향으로 원하는 만큼 이동할 수 있다.
    - [x] 해당 위치에 같은 색의 기물이 없으면 대각 방향으로 원하는 만큼 이동할 수 있다.

- KingState
    - [x] 직선 혹은 대각 방향으로 한 칸 이동할 수 있다.

#### piece

- [x] 이름을 가진다.
- [x] 피스 타입을 가진다.
- [x] 움직임 전략을 가진다.

#### Position

- [x] File 과 Rank 를 갖는다.
- [x] 다른 포지션과의 Rank 순서 차이를 구할 수 있다.
- [x] 다른 포지션과의 File 순서 차이를 구할 수 있다.
- [x] 다른 포지션과의 직선 경로 Position 을 얻을 수 있다.
    - [예외] 직선이 아니면 예외가 발생한다.

#### Board

- [x] Piece 들의 위치를 초기화 한다.
- [x] 보드는 자신의 판 정보를 반환할 수 있다.
- [x] 시작 Position 과 목표 Position 를 입력하면 Piece를 움직일 수 있다.

### 뷰

#### InputView

- [x] start 와 end 를 입력 받는다.

#### OutputView

- [x] 체스판을 출력한다.
