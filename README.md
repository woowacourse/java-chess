# java-chess

체스 미션 저장소

## 클래스 다이어그램

```mermaid
classDiagram
    Game --> Piece
    Game --> Position

    Rank <-- Position
    File <-- Position


    Position --> Movement
    Piece --> Movement
    <<enumeration>> Rank
    <<enumeration>> File

    class Position {
        Rank rank
        File file
    }

    class Game {
        Map(Position, Piece) chessBoard
    }


```

### 체스 말들의 상속 관계

```mermaid
classDiagram
    Piece <|-- Pawn
    Piece <|-- King
    Piece <|-- Queen
    Piece <|-- Rook
    Piece <|-- Bishop
    Piece <|-- Knight
    Piece <|-- EmptyPiece
```

# 기능 요구 사항

### DB적용 시나리오

- [ ] 게임 시작 시, 진행중인 체스 게임 방들을 불러온다
- [ ] 기존 체스 게임을 재시작할지, 새로운 게임을 시작할지 결정한다.
    - [ ] 재시작의 경우, DB에서 게임 정보를 불러온다.
    - [ ] 새로운 게임의 경우, 새로운 체스게임을 생성한다.
- [ ] 게임을 진행한다.
    - [ ] end입력 시, 현재 진행중인 체스 정보를 저장한다.
    - [ ] king이 잡혀서 게임이 종료된 경우, DB의 게임정보에 이를 업데이트한다.

### Position

- [x] domain.piece.File, Rank로 위치 정보를 생성한다.
    - [x] col정보는 File인스턴스로 나타낸다.
    - [x] row정보는 Rank인스턴스로 나타낸다.
- [x] domain.piece.File, Rank의 증분을 계산한다.
- [x] Target position까지의 이동 경로를 반환한다.
    - [x] 요청받은 movement를 기준으로 해당 방향으로 한 칸 움직인다.

### Piece

- [x] Source position에서 Target position으로 가는 경로를 반환한다.
    - [x] Target position이 해당 말이 이동할 수 없는 위치일 경우 예외가 발생한다.
- [x] source piece와 target piece가 같은 팀인지 확인한다.
- [x] source piece와 target piece가 다른 팀인지 확인한다.

### Pawn

- [x] 이동할 수 있는지 확인한다.
    - [x] White 진영은 위로, Black 진영은 아래로 움직인다.
    - [x] 첫 움직임은 2칸까지, 나머지 움직임은 1칸까지 움직일 수 있다.
    - [x] 상대편 말을 잡을 때는 대각선으로만 움직일 수 있다.
- [x] 입력 받은 Target position으로 가는 경로를 반환한다.

### Bishop

- [x] 이동할 수 있는지 확인한다.
    - [x] 대각선으로만 움직일 수 있다.
    - [x] Target에 같은 진영 말이 있으면 움직일 수 없다.
- [x] 입력 받은 Target position으로 가는 경로를 반환한다.

### King

- [x] 이동할 수 있는지 확인한다.
    - [x] 한 칸이면, 모든 방향으로 움직일 수 있다.
    - [x] Target에 같은 진영 말이 있으면 움직일 수 없다.
- [x] 입력 받은 Target position으로 가는 경로를 반환한다.

### Queen

- [x] 이동할 수 있는지 확인한다.
    - [x] 대각선, 수직, 수평으로 움직일 수 있다.
    - [x] Target에 같은 진영 말이 있으면 움직일 수 없다.
- [x] 입력 받은 Target position으로 가는 경로를 반환한다.

### Rook

- [x] 이동할 수 있는지 확인한다.
    - [x] 수직 방향으로만 움직일 수 있다.
    - [x] Target에 같은 진영 말이 있으면 움직일 수 없다.
- [x] 입력 받은 Target position으로 가는 경로를 반환한다.

### Knight

- [x] 이동할 수 있는지 확인한다.
    - [x] 수직 2칸 + 수평 1칸 또는 수직 1칸 + 수평 2칸으로 움직일 수 있다.
    - [x] Target에 같은 진영 말이 있으면 움직일 수 없다.
- [x] 입력 받은 Target position으로 가는 경로를 반환한다.
    - [x] Knight는 아군 말을 뛰어 넘을 수 있기 때문에 빈 경로를 반환한다.

### Game

- [x] 각각의 Rank와 File을 표현하도록 체스판을 초기화한다.
    - [x] Rank(가로 위치)는 왼쪽부터 a ~ h이다.
    - [x] File(세로 위치)는 아래부터 위로 1 ~ 8이다.
- [x] Source position, Target position를 입력 받아 말을 이동시킨다.
    - [x] 이동 경로에 같은 진영의 말이 존재하는지 확인한다.
    - [x] 상대방의 말을 움직일 경우, 예외가 발생한다.
    - [x] Source position에 말이 존재하지 않는 경우, 예외가 발생한다.
    - [x] 목표 위치에 같은 편 말이 존재할 경우, 예외가 발생한다.
    - [x] 목표 위치가 말의 이동 가능 범위에 벗어나는 경우, 예외가 발생한다.
    - [x] 상대방의 말 위치로 이동한 경우, 상대방 말은 죽고 이동한 말이 그 위치를 대체한다.
        - [x] 해당 위치의 Piece는 EmptyPiece로 대체한다.

### ChessBoardGenerator

- [x] 체스 판과 규칙에 맞는 초기 말들을 생성한다.

### Movement

- [x] 어느 방향으로도 움직이지 않으면 예외가 발생한다.
- [x] 1칸 이내 움직임인지 확인한다.
- [x] 2칸 이내 움직임인지 확인한다.
- [x] 상, 하, 좌, 우(수직) 방향성의 움직임인지 확인한다.
- [x] 대각선 방향성의 움직임인지 확인한다.
- [x] 위(1,2사분면) 방향성의 움직임인지 확인한다.
- [x] 아래(3,4사분면) 방향성의 움직임인지 확인한다.
- [x] 오른쪽(1,4사분면) 방향성의 움직임인지 확인한다.
- [x] 왼쪽(2,3사분면) 방향성의 움직임인지 확인한다.
- [x] Knight의 움직임인지 확인한다.

### File (enum)

- [x] Source file과 Target file간 거리 차이를 반환한다.
- [x] 다음 순서의 값을 반환한다.
- [x] 이전 순서의 값을 반환한다.

### Rank (enum)

- [x] Source rank와 Target rank간 거리 차이를 반환한다.
- [x] 다음 순서의 값을 반환한다.
- [x] 이전 순서의 값을 반환한다.

### Calculator

- [ ] 체스판 정보를 토대로 Black, White진영의 점수를 계산한다.

### InputView

- [x] 게임 커맨드를 입력받는다.

### OutputView

- [x] 게임 가이드 메시지를 출력한다.
- [x] 체스판 현황을 출력한다.
- [x] 현재 움직일 진영을 출력한다.
- [ ] 체스 게임 결과를 출력한다.

### GameCommand

- [x] 사용자 입력에 대치되는 커맨드를 반환한다.

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
