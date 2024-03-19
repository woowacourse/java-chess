# java-chess

## 용어 정리

- Rank: 체스판의 가로 줄
- File: 체스판의 세로 줄
- Piece: 체스 각각의 기물
- P, p: Pawn(폰)
    - 첫 이동 시에는 두 칸을 전진할 수 있다. 그 이후에는 한 칸씩만 전진할 수 있다.
- N, n: Night(나이트)
    - 'L' 모양으로 움직입니다. 다른 기물들을 뛰어넘을 수 있다.
- B, b: Bishop(비숍)
    - 대각선 방향으로 어디든지 여러 칸을 움직일 수 있다.
- R, r: Rook(룩)
    - 수직 또는 수평 방향으로 어디든지 여러 칸을 움직일 수 있다.
- Q, q: Queen(퀸)
    - 대각선, 수직, 수평 방향으로 어디든지 여러 칸을 움직일 수 있다.
- K, k: King(킹)
    - 한 번에 한 칸씩 대각선 방향이나 수직, 수평 방향으로 이동할 수 있다.

## 구현할 기능 목록

### 입출력 기능

#### 입력

-  시작/종료 명령어를 입력한다. (start/end)

#### 출력

- 초기화된 체스판 현황을 출력한다.
```text
<프로그램 실행 결과>
체스 게임을 시작합니다.
게임 시작은 start, 종료는 end 명령을 입력하세요.
start
RNBQKBNR
PPPPPPPP
........
........
........
........
pppppppp
rnbqkbnr

end
```

### 비즈니스 기능

- 체스판을 초기화한다.
  - 체스판은 8x8로 이루어져 있다.
  - 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8이다.
  - 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.
  - 검은색 진영은 체스판 위에 배치하고 흰색 진영은 아래에 배치한다.
  - 폰은 전부 Rank 2와 Rank 7에 위치 한다.
  - 이외의 기물은 RNBQKBNR(rnbqkbnr) 순서로 Rank 1과 Rank 8에 위치한다.
