# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 구현 기능

### 1. 체스판 초기화

- 체스판은 8x8 크기이다.
- 각 플레이어는 하나의 킹, 퀸과 2개의 비숍, 나이트, 룩, 8개의 폰을 가진다.
- 각 플레이어는 White 혹은 Black 진영을 가지고 시작한다.
- 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

### 2. 말 이동

#### 모든 기물

- 같은 팀 기물이 있는 곳으로 이동할 수 없다.
- 상대 팀 기물이 있는 곳으로 이동하면 상대 기물이 잡힌다.

#### 킹, 퀸, 룩, 비숍, 폰

- 이동 경로에 다른 기물이 있는 경우 뛰어 넘을 수 없다.

#### 니이트

- 이동 경로에 다른 기물이 있더라도 뛰어넘을 수 있다.

### 킹

- 상, 하, 좌, 우, 대각선 1칸씩 이동

<img src = "./image/king_move.png" alt="./image/king_move.png" width="65%">

### 퀸

- 상, 하, 좌, 우, 대각선 한 방향으로 칸수 제한 없이 이동

<img src = "./image/queen_move.png" alt="./image/king_move.png" width="65%">

### 룩

- 상, 하, 좌, 우 한 방향으로 칸수 제한 없이 이동

<img src = "./image/rook_move.png" alt="./image/rook_move.png" width="65%">

### 비숍

- 대각선 한 방향으로 칸수 제한 없이 이동

<img src = "./image/bishop_move.png" alt="./image/bishop_move.png" width="65%">

### 나이트

- 8 방향으로 이동 가능

<img src = "./image/knight_move.png" alt="./image/knight_move.png" width="65%">

### 폰

- 앞으로 1칸 이동할 수 있다.
    - 예외적으로 초기 위치에 있을 때 최대 2칸 이동할 수 있다.
- 상대편 기물을 잡을 경우, 대각선 방향 잡을수 있다.

<img src = "./image/pawn_move.png" alt="./image/pawn_move.png" width="65%">

## 3. 승패 및 점수

- 상대편 King을 잡는 경우 게임에서 승리한다.
    - King이 잡혔을 때 게임은 종료된다.
- 현재 남아 있는 말에 대한 점수를 구한다.
    - Queen은 9점
    - Rook은 5점
    - Bishop은 3점
    - Knight는 2.5점
    - Pawn은 1점
        - 같은 세로줄에 같은 팀의 폰이 있는 경우 1점이 아닌 0.5점을 준다.

- 한 번에 한 쪽의 점수만을 계산해야 한다.
- 남은 기물의 점수의 합이 높은 쪽이 승리한다.

## 4. 게임 커맨드

- `start`: 게임을 시작하고 체스판을 초기화 한다.
- `end`: 게임을 종료한다.
- `move source위치 target위치`: source위치에 있는 기물을 target위치로 옮긴다.
- `status`: 각 진영의 점수를 출력하고 어느 진영이 이겼는지 결과를 보여준다.