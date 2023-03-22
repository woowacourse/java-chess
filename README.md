# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

```mermaid
classDiagram
    class Board {
        -Map~Position|Piece~: Squares
    }

    class Piece {
        <<abstract>>
        -Role: role
        -Team: Team
        +canMove(Position src, Position dst)
    }

    class Position {
        - int: x
        - int: y
    }

    class Role {
        <<enumeration>>
        KING,
        QUEEN,
        ROOK,
        BISHOP,
        KNIGHT,
        PAWN,
        EMPTY
    }

    class Team {
        <<enumeration>>
        WHITE,
        BLACK,
        NONE
    }
```

```mermaid
flowchart BT
    Pawn --> Piece
    King --> Piece
    Knight --> Piece
    Bishop --> Piece
    Queen --> Piece
    Rook --> Piece
    Empty --> Piece
```

```mermaid
classDiagram
    direction BT
    class BlackCheckedState
    class BlackTurnState
    class BlackWinState
    class EndState
    class GameState {
        <<Interface>>
    }
    class NoneWinState
    class RunningState
    class WaitingState
    class WhiteCheckedState
    class WhiteTurnState
    class WhiteWinState

    BlackCheckedState --> CheckedState
    BlackTurnState --> RunningState
    BlackWinState --> EndState
    CheckedState --> RunningState
    EndState ..> GameState
    NoneWinState --> EndState
    RunningState ..> GameState
    WaitingState ..> GameState
    WhiteCheckedState --> CheckedState
    WhiteTurnState --> RunningState
    WhiteWinState --> EndState 
```

```mermaid
stateDiagram
    ChessGame --> WaitingState
    state WaitingState {
        [*] --> startGame
        [*] --> movePiece
        [*] --> getBoard
        startGame --> [*]
        movePiece --> Exception
        getBoard --> Exception
    }
```

```mermaid
stateDiagram
    ChessGame --> RunningState
    state RunningState {
        [*] --> startGame
        [*] --> movePiece
        [*] --> getBoard
        startGame --> Exception
        movePiece --> [*]
        getBoard --> [*]
    }
```

```mermaid
stateDiagram
    ChessGame --> EndState
    state EndState {
        [*] --> startGame
        [*] --> movePiece
        [*] --> getBoard
        startGame --> Exception
        movePiece --> Exception
        getBoard --> Exception
    }
```

## 기능 요구 사항

### 게임 진행

- [X] 체스판의 가로 위치는 왼쪽부터 a ~ h 이다.
- [X] 체스판의 세로 위치는 아래부터 1 ~ 8 이다.
- [X] 각 진영은 검은색(대문자)와 흰색(소문자) 편으로 구분한다.
- [x] 게임을 시작하고, start를 입력해야 체스판을 출력한다.
- [x] 게임을 시작하고, end를 입력하면 게임을 종료한다.
- [X] 게임을 시작하면, 보드에 말이 세팅되어야 한다.
- [X] 체스판이 초기화되고, `move source위치 target위치`를 입력하면 체스 말이 이동한다.
- [X] 체스판이 초기화되지 않고 `move`를 입력하면 예외가 발생한다.
- [X] 체스말이 움직일 수 없는 위치로 이동하면 예외가 발생한다.
- [X] 상대팀을 체크메이트 상태로 만들면 승리한다.
- [X] 게임 도중 각 진영의 점수를 출력하고, 어느 진영이 이겼는지 알 수 있다.
- [X] 시작 시 누가 먼저 말을 움직일지는 랜덤하게 선택한다.
- [X] 나의 턴이 아닌데 말을 움직이면 예외가 발생한다.
- [X] 각 말의 점수가 정확히 계산되어야 한다.
  - [X] 같은 세로 줄에 있는 `Pawn`은 각 0.5점씩 계산해야 한다.

### 예외 상황

- [X] 커맨드가 `start`, `end`, `move`가 아닌 경우 예외가 발생한다.
- [X] 보드가 세팅되지 않았는데 `move`를 입력하는 경우 예외가 발생한다.
- [X] `move`에 잘못된 위치를 입력하면 예외가 발생한다.
  - [X] source와 target의 위치가 동일하면 예외가 발생한다.
  - [X] source와 target의 위치가 체스판을 벗어나는 경우 예외가 발생한다.
  - [X] target에 같은 팀의 말이 있는 경우 예외가 발생한다.
  - [X] source에 말이 없는 경우 예외가 발생한다.
- [X] 나의 턴이 아닌데, 말을 움직이면 예외가 발생한다.

### 움직임

- [X] 체스말이 역할에 맞게 올바르게 움직일 수 있어야 한다.
- [X] KNIGHT를 제외한 모든 말은 이동 경로 상에 말이 있으면 지나갈 수 없다.

### PAWN

- [X] 화이트는 y값이 `증가`만 가능하고, 블랙은 y값이 `감소`만 가능하다.
- [X] 화이트는 y가 1일때, 블랙은 y가 6일때만 2칸 움직일 수 있다.
- [X] 길이가 1만큼 떨어진 대각선으로 이동하는 경우, 해당 위치에 상대 말이 있어야 한다.

### ROOK

- [X] x, y 거리 상관 없이 `직선 방향`으로 움직일 수 있다.

### BISHOP

- [X] x, y 거리 상관 없이 `대각선`으로만 움직일 수 있다.

### KNIGHT

- [X] 상,하,좌,우 대각선 1칸 기준 바깥쪽 한칸에 대해 1칸 움직일 수 있다.
- [X] `이동 경로 상에 말이 있어도 이동이 가능하다.`

### QUEEN

- [X] x, y 거리 상관 없이 `직선`, `대각선`으로 움직일 수 있다.

### KING

- [X] `직선`, `대각선` 1칸 거리만 움직일 수 있다.
