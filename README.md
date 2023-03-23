# java-chess

# 기능 목록

# 🚀 도메인
## Piece
- [x] 진영을 가진다 (Color)
- [x] 타입을 가진다 (PieceType)
- [x] 종류별로 움직일 수 있는 방향을 가진다
  - [x] 움직일 수 있는 방향인지 판단한다
  - [x] 움직일 수 있는 거리인지 판단한다

### 1) Rook
- [x] 점수 : 5점
- [x] 움직일 수 있는 방향 : N, E, S, W
- [x] 움직일 수 있는 거리 : 상관 x

### 2) Bishop
- [x] 점수 : 3점
- [x] 움직일 수 있는 방향 : NE, SE, SW, NW
- [x] 움직일 수 있는 거리 : 상관 x

### 3) Queen
- [x] 점수 : 9점
- [x] 움직일 수 있는 방향 : N, NE, E, SE, S, SW, W, NW
- [x] 움직일 수 있는 거리 : 상관 x

### 4) King
- [x] 점수 : 0점
- [x] 움직일 수 있는 방향 : N, NE, E, SE, S, SW, W, NW
- [x] 움직일 수 있는 거리 : 1
- [x] 잡히는 경우 경기가 끝난다

### 5) Knight
- [x] 점수 : 2.5점
- [x] 움직일 수 있는 방향 : ENE, ESE, SSW, SSE, WNW, WSW, NNW, NNE
- [x] 움직일 수 있는 거리 : 제곱의 합이 5 (ex. 2*2+1*1=5)
- [x] 경로에 다른 기물이 있어도 이동할 수 있다

### 6) Pawn
- [ ] 점수
  - [x] 기본 점수 : 1점
  - [ ] 같은 세로줄에 같은 색의 폰이 있는 경우 : 0.5점
- [x] 움직일 수 있는 방향
  - [x] White 팀 : N, NE, NW
  - [x] Black 팀 : S, SE, SW
- [x] 움직일 수 있는 거리
  - [x] 처음 움직임 : 1 또는 2
  - [x] 그 외 : 1
- [x] 대각선에 상대말이 있을 때에만 대각선으로 이동할 수 있다
- [x] 단순 이동은 위아래만 가능하다

### +) PieceFactory : 기본 세팅에 필요한 Piece들을 만든다
### +) PieceType : Piece의 종류를 나타내는 enum

## Position
- [x] file과 rank로 이루어진다
- [x] (A,1) ~ (H,8)
- [x] 다른 Position으로의 방향을 계산한다
- [x] 다른 Position으로의 거리를 계산한다
- [x] 다른 방향으로 이동할 수 있다

### +) Rank : 체스판의 가로줄을 나타내는 enum
### +) File : 체스판의 세로줄을 나타내는 enum
### +) Direction : 이동 방향을 나타내는 enum

## Board
- [x] 초기화한다 (기본 체스보드 세팅)
- [x] 특정 Position에 있는 Piece를 반환한다
- [x] 움직일 수 있는 Piece인지 확인한다
  - [x] 빈 Piece인지
  - [x] 같은 Color인지
- [x] 이동경로 사이에 다른 Piece가 있는지 확인한다
- [x] Piece를 교체한다
- [x] Rank에 위치한 Piece들을 반환한다
- [x] 각 진영의 점수를 계산한다

## ChessGame
- [x] 게임을 시작한다
- [x] Piece를 움직인다
  - [x] King이 잡혔는지 확인한다
- [x] 각 진영의 점수를 반환한다
- [x] 게임을 끝낸다

### +) Color : Piece의 색을 나타내는 enum
### +) GameStatus : 게임의 상태를 나타내는 enum

## CommandLine
- [x] 입력을 검증한다
  - [x] start
  - [x] move - 2개의 추가 인자를 가져야 한다
  - [x] status
  - [x] end
- [x] 메인 명령 토큰을 제외한 나머지 인자들을 반환한다

# 🖋 UI
## 입력
- [x] 명령을 입력받는다

## 출력
- [x] 게임시작 메시지를 출력한다
- [x] 보드의 상태를 출력한다
- [x] 각 팀의 현재 점수를 출력한다
- [x] 에러를 출력한다
