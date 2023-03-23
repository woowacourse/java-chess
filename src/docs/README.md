## 도메인 객체 그래프

```mermaid
graph TD
  ChessController --> InputView
  ChessController --> OutputView
  ChessController --> ExecuteCommand

  ChessController --> ChessGame
  ChessGame --> Board

  Square --> File
  Square --> Rank

  BoardFactory --> Board

  Board --> Square
  Board --> piece

  piece --> Color
  piece --> PIECE_TYPE

  subgraph PIECE_TYPE
    direction BT
    Black_Pawn -.-> pieceType
    White_Pawn -.-> pieceType
    Rook -.-> pieceType
    Bishop -.-> pieceType
    Knight -.-> pieceType
    Queen -.-> pieceType
    King -.-> pieceType
  end

  PIECE_TYPE --> STRATEGY

  subgraph STRATEGY
    direction BT
    DirectStrategy -.-> strategy
    SlidingStrategy -.-> strategy
  end

  STRATEGY --> DirectVector
  STRATEGY --> SlidingVector
```

## 구현 기능 목록

### 체스 게임

- [x] 게임을 시작한다.
- [x] 점수를 계산한다.
- [x] 기물을 이동시킨다.
- [x] 게임을 종료한다.
- [x] 게임 진행을 확인한다.

### 체스 보드

- [x] 특정 칸의 기물을 확인한다.
- [x] 기물을 이동시킨다.
- [x] 해당 색의 기물 점수를 계산한다.

### 체스 칸

- 세로줄 (File)
    - [x] 왼쪽부터 a ~ h이다.
    - [x] 파일 간 거리를 계산한다.
    - [x] 다음 파일을 반환한다.
    - [x] 이전 파일을 반환한다.
- 가로줄 (Rank)
    - [x] 아래부터 1 ~ 8이다.
    - [x] 랭크 간 거리를 계산한다.
    - [x] 다음 랭크를 반환한다.
    - [x] 이전 랭크를 반환한다.
- [x] 위치에 알맞은 체스 칸을 생성한다.
- [x] 다음 체스 칸을 반환한다.
- [x] 해당 색의 폰 초기 위치인지 확인한다.

### 체스 기물

- 여러 가지 기물이 존재한다.
    - 폰
    - 룩
    - 나이트
    - 비숍
    - 퀸
    - 킹
- 색을 가진다.
    - [x] 흑과 백이 존재한다.
    - [x] 검은색인지 확인한다.
    - [x] 같은 색인지 확인한다.
- [x] 움직일 수 있는지 확인한다.
- [x] 폰인지 확인한다.

### 전략

- [x] 경로를 찾는다.

### 벡터

- [x] 움직이려는 방향으로 갈 수 있는지 확인한다.
- [x] 방향의 다음 파일을 반환한다.
- [x] 방향의 다음 랭크를 반환한다.

### 입력

- [x] 게임 실행 명령을 입력한다.

### 출력

- [x] 게임 시작 문구를 출력한다.
- [x] 체스판을 출력한다.
- [x] 체스 상태에 따른 결과를 출력한다.
- [x] 게임 종료 문구를 출력한다.
- [x] 에러 메시지를 출력한다.
