## 체스 기능 요구 사항

### 1단계 체스판 초기화
- 체스 게임 시작 문장 출력
- "start"를 입력 받는다.
- "end"를 입력받는다.
  - 이외의 입력이 들어오면 예외

- 체스판을 초기화한다.

## 2단계 체스 말 이동
- "move {source 위치} {target 위치}" 형식으로 입력받는다.
  - 가로 위치는 a~h, 세로 위치는 1~8 사이로 입력 받는다.
- 기물을 각 이동 규칙에 따라 움직이게 한다.

## 3단계 승패 및 점수
- "status"를 입력 받아 각 진영의 점수를 출력한다.
- King이 잡혔을 때 게임을 종료해야 한다.


## 도메인 설계
###체스판
- Board
- BoardInitializer
### 명령어
  - Command
  - End
  - Move
  - Start
  - Status
### 방향
  - 전략
  - DirectionStrategy
    - \+ 모든 말의 DirectionStrategy
  - Direction
  - BasicDirection
  - DiagonalDirection
  - KnightDirection
### 기물
- Color(Black, White)
- King, Queen, Knight, Bishop, Rook, Pawn
### 위치
- Position
- UnitPosition
### 상태
- State
- Ready
- Running
- RunningBlackTurn
- RunningWhiteTurn
- Finished