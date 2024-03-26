# java-chess

## 규칙 정리

- Rank: 체스판의 가로 줄 (1 ~ 8)
- File: 체스판의 세로 줄 (a ~ h)
- 각 진영은 BLACK과 WHITE로 구분한다.
- Piece: 체스 각각의 기물
- Pawn(폰)
    - 첫 이동 시에는 두 칸을 전진할 수 있다. 그 이후에는 한 칸씩만 전진할 수 있다.
- Knight(나이트)
    - 'L' 모양으로 움직입니다. 다른 기물들을 뛰어넘을 수 있다.
- Bishop(비숍)
    - 대각선 방향으로 어디든지 여러 칸을 움직일 수 있다.
- Rook(룩)
    - 수직 또는 수평 방향으로 어디든지 여러 칸을 움직일 수 있다.
- Queen(퀸)
    - 대각선, 수직, 수평 방향으로 어디든지 여러 칸을 움직일 수 있다.
- King(킹)
    - 한 번에 한 칸씩 대각선 방향이나 수직, 수평 방향으로 이동할 수 있다.

## 구현할 기능 목록

### 입출력 기능

#### 입력

- [게임 준비] 시작/종료 명령어를 입력한다.
  - start/end가 아니면 예외가 발생한다.
- [게임 중] move, 종료, 점수 출력 명령어를 입력한다.
  - 각각 `move (source위치) (target위치)`, `end`, `status` 형식이 아니면 예외가 발생한다.

#### 출력

- 초기 체스판 현황을 출력한다.

- 각 이동마다 체스판을 출력한다.

- 현재 차례의 남아 있는 기물들의 점수 합을 출력한다.

### 비즈니스 기능

- 체스판을 초기화한다.
    - 체스판은 8x8로 이루어져 있다.
    - 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8이다.
    - 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.
    - 검은색 진영은 체스판 위에 배치하고 흰색 진영은 아래에 배치한다.
    - 폰은 전부 Rank 2와 Rank 7에 위치 한다.
    - 이외의 기물은 RNBQKBNR(rnbqkbnr) 순서로 Rank 1과 Rank 8에 위치한다.
      ```text
      RNBQKBNR 8 | BLACK
      PPPPPPPP 7 | BLACK
      ........ 6
      ........ 5
      ........ 4
      ........ 3
      pppppppp 2 | WHITE
      rnbqkbnr 1 | WHITE
      
      abcdefgh
      ```

- 체스판에 Source 또는 Target 위치에 기물이 있는지 찾는다.
    - Source 위치에 기물이 없으면 예외가 발생한다.
    - 올바른 차례인 Source 기물이 아니면 예외가 발생한다.

- 체스 기물이 이동한다.
    - 경로에 기물이 있다면 이동할 수 없다.
    - Target 위치에 아군 기물이 있으면 이동할 수 없다.
  - **Pawn**
      - 폰은 1칸씩 전진한다.
          - [WHITE 진영] Rank2에 있는 경우 2칸 혹은 1칸 움직일 수 있다.
          - [BLACK 진영] Rank6에 있는 경우 2칸 혹은 1칸 움직일 수 있다.
      - 전진한 최종 위치에 어느 기물이든 있으면 움직일 수 없다.
      - 뒤로 갈 수 없다.
      - 상대편 기물이 대각선 방향에 있으면 움직일 수 있다.
  - **Knight**
      - 나이트는 'L'자로 움직인다.
      - (file, rank)
          - (file+2, rank+1), (file+2, rank-1), (file-2, rank+1), (file-2, rank-1)
          - (file+1, rank+2), (file+1, rank-2), (file-1, rank+2), (file-1, rank-2)
      - 유일하게 다른 기물을 뛰어넘을 수 있다.
  - **Bishop**
      - 대각선 방향으로 움직인다.
      - 원하는 만큼 움직일 수 있다.
  - **Rook**
      - 상하좌우로 움직인다.
      - 원하는 만큼 움직일 수 있다.
  - **Queen**
      - 상하좌우 대각선으로 움직인다.
      - 원하는 만큼 움직일 수 있다.
  - **King**
      - 자기 자신을 기준으로 상하좌우 대각선으로 움직인다.
      - 1칸씩 움직일 수 있다.

- 현재 남아 있는 기물에 대한 점수를 구할 수 있다.
  - Queen: 9점
  - Rook: 5점
  - Bishop: 3점
  - Knight: 2.5점
  - King: 0점
  - Pawn: 1점 (세로줄에 같은 진영의 Pawn은 각 0.5점)
