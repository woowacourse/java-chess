## 페어프로그래밍을 위한 규칙

- 타이머 : 15분
- 타이머가 울리면 마무리하지 않고 넘기기  (이어서 못치면 사과하기)
- TDD 사이클 단위 커밋하기

## 이번 미션의 목표

- SRP
- 모든 변수명에 final 붙이기
    - 당연히 메서드 파라미터에도 final을 붙인다.
- 모든 원시값과 문자열을 포장한다.
- 일급 컬렉션을 쓴다.
- 객체지향생활체조를 모두 만족한다.
- 모든 클래스에 abstract 혹은 final을 추가한다.
- 추상 클래스의 메서드에는 abstract 혹은 final을 추가한다.

## 추가된 요구 사항

- 도메인의 의존성을 최소한으로 구현한다.
- 한 줄에 점을 하나만 찍는다.
- **게터/세터/프로퍼티**를 쓰지 않는다.
    - (View를 위한 getter는 상관이 없다.)
- 모든 객체지향 생활 체조 원칙을 잘 지키며 구현한다.
- [프로그래밍 체크리스트](https://github.com/woowacourse/woowacourse-docs/blob/master/cleancode/pr_checklist.md)의 원칙을 지키면서 프로그래밍
  한다.

## 기능 요구 사항

### **1단계 - 체스판 초기화**

- 콘솔 UI에서 체스 게임을 할 수 있는 기능을 구현한다.
- 1단계는 체스 게임을 할 수 있는 체스판을 초기화한다.
- 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

- [x]  체스판 팩토리
    - [x]  캐싱 (콘솔 게임이라도 여러판을 하게 되면 캐싱하는 것이 유리하지 않나)
    - [x]  체스판을 제공

- [x]  ChessBoard - 일급 컬렉션
    - [x]  List<Rank>
    - [x]  가로 위치는 왼쪽부터 a ~ h
    - [x]  세로 위치는 아래부터 위로 1 ~ 8

- [x]  InitRank - enum (value → List<Type>) : 해당 이넘을 이용해서 체스판이 Rank를 만든다.
    - [x]  폰만 생성되는 라인 * 2
    - [x]  아무것도 없는 라인 * 4
    - [x]  RNBQKBNR이 생성되는 라인 * 2
        - [x]  Collections.reverse()

- [x]  Rank - 일급 컬렉션
    - [x]  List<peice>
    - [x]  initRank를 Rank로 반환하는 정적 메서드

- [x]  Piece
    - [x]  Color - enum
        - [x]  BLACK
        - [x]  WHITE
    - [x]  Type - enum → 뷰에서 할 행동 : 알파벳을 미리 넣어두고, 색깔에 따라 대소문자 변경)
        - [x]  NONE(.)
        - [x]  킹 (k)
        - [x]  퀸 (q)
        - [x]  나이트 (n)
        - [x]  폰 (p)
        - [x]  룩 (r)
        - [x]  비숍 (b)

### **2단계 - 말 이동**

- 콘솔 UI에서 체스 게임을 할 수 있는 기능을 구현한다.
- 체스 말의 이동 규칙을 찾아보고 체스 말이 이동할 수 있도록 구현한다.
- **`move source위치 target위치`**을 실행해 이동한다.
- 체스판의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

### ChessBoard 패키지

- [x]  ChessBoard
    - [x]  `List<Rank>`
    - [x]  `colorTurn`
    - [x]  `move(Position startPoint, Position endPoint)`
    - `startPoint`의 상태를 `Empty`로 바꿔주고, `endPoint`의 상태를 `startPoint`의 `SquareStatus`으로 바꿔준다.
    - [x]  PawnMove
    - 대각인 경우, 상대방의 기물이 있으면
        - move
    - 직선인 경우, 기물이 존재하지 않으면
        - move

- [x]  InitRank : Enum (Value : `List<SquareStatus>`)
    - [x]  초기 Rank 캐싱용 Enum

- [x]  Rank
    - [x]  `List<Square>`
    - [x]  특수 룰 적용 시 사용가능

- [x]  Square
    - [x]  `SquareStatus`
    - [x]  beEmpty()
        - 빈칸으로 상태 변경
    - [x]  bePiece(final Piece piece)
        - 파라미터로 받은 Peice로 상태 변경
    - [x]  getSquareStatus()

- [x]  SquareStatus : 인터페이스

- [x]  Empty `implements SquareStatus`

### Type 패키지

- [x]  Type : 인터페이스

- [x]  EmptyType : enum
    - [x]  Empty

- [x]  PieceType : enum
    - [x]  King
    - [x]  Queen
    - [x]  Knight
    - [x]  Bishop
    - [x]  Rook
    - [x]  Pawn

### Piece 패키지

- [x]  Piece `implements SquareStatus` : 추상 클래스
    - [x]  `Color`
    - [x]  `PieceType`
    - [x]  findRoute(Position startPoint, Position endPoint) : Route를 반환하는 추상 메서드

- [x]  Color : enum
    - [x]  Black
    - [x]  White

- [x]  King `extends Piece`
    - [x]  `findRoute(Position startPoint, Position endPoint)`
    - king이 움직일 수 있는 거리는 1칸이므로 빈 리스트를 반환한다.
    - [x] `isMovable(Position startPoint, Position endPoint)`
    - diffX의 절댓값과 diffY의 절댓값의 합이 2 이하

- [x]  Queen `extends Piece`
    - [x]  `findRoute(Position startPoint, Position endPoint)`
    - [x]  `isMovable(Position startPoint, Position endPoint)`
    - diffX 혹은 diffY가 0 || diffX의 절댓값 - diffY의 절댓값이 0


- [x]  Knight `extends Piece`
    - [x]  `findRoute(Position startPoint, Position endPoint)`
    - knight는 다른 기물을 뛰어넘을 수 있으므로 빈 리스트를 반환한다.
    - [x]  `isMovable(Position startPoint, Position endPoint)`
    - diffX, diffY의 절댓값이 1 이랑 2
      → set.contain(1) && set.contain(2)

- [x]  Bishop `extends Piece`
    - [x]  `findRoute(Position startPoint, Position endPoint)`
    - [x]  `isMovable(Position startPoint, Position endPoint)`
    - diffX의 절댓값 - diffY의 절댓값이 0

- [x]  Rook `extends Piece`
    - [x]  `findRoute(Position startPoint, Position endPoint)`
    - [x] `isMovable(Position startPoint, Position endPoint)`
    - diffX 혹은 diffY가 0
      → set.size == 2 && set.contain(0)

- [x]  Pawn `extends Piece`
    - [x]  `findRoute(Position startPoint, Position endPoint)`
    - pawn이 움직일 수 있는 거리는 1칸이므로 빈 리스트를 반환한다.
    - 그러나 색깔이 black일때, startPoint의 y값이 7, white일 때, endPoint의 값이 2이면
      길이가 1인 리스트를 반환한다.

### Coordinate 패키지

- [x]  Position : 값객체
    - [x]  `x`
    - [x]  `y`
    - [x]  64개의 Position 캐싱
    - [x]  `diffX(final Position otherPosition)`
    - [x]  `diffY(final Position otherPosition)`

- [x]  PositionFactory
    - [x]  enum
    - [x]  입력받은 좌표로 `Position` 생성

- [x]  Route
    - [x]  `List<Position>`
    - [x]  기물의 이동 경로에 위치한 `Postion`을 시작점과 도착점을 제외하고 반환하는 `List`

## 요구 사항 분석 후 추가 고려 사항

기물 이동의 조건

1. 해당 기물이 이동할 수 있는 경로
2. 경로 상에 장애물이 있는지 (장애물 : 모든 기물)
3. 도착지에 장애물이 있는지 (장애물 : 나의 기물)

→ 기물 스스로 알 수 있는 것 : 본인이 도착지에 가도 되는지 여부와, 도착지까지의 경로

→ 체스판이 알 수 있는 것 : 도착지까지의 경로상에 기물이 있는지, 도착지에 나의 기물이 있는지

체스판에게 도착지에 대한 정보는 알려줄 필요가 없다.

갈 수 없으면 예외처리하고, 갈 수 있으면 리스트를 반환하면 되기 때문

## 질문 사항

- ChessGame의 책임이 많다고 볼 수 있는 지 궁금합니다.
- Empty 객체의 존재
  - 상태를 표현하기 위해서 Empty 객체를 만들었는데, 32개의 Piece만으로도 충분한데 굳이 32개의 Empty가 추가로 생기는 게 괜찮을까요?
- Type Interface의 바디에 아무것도 들지 않았는데 이런 방법도 괜찮을까요?

### **3단계 - 승패 및 점수**

1. 기능 요구 사항
- 체스 게임은 상대편 King이 잡히는 경우 게임에서 진다. King이 잡혔을 때 게임을 종료해야 한다.
- 체스 게임은 현재 남아 있는 말에 대한 점수를 구할 수 있어야 한다.
- "status" 명령을 입력하면 각 진영의 점수를 출력하고 어느 진영이 이겼는지 결과를 볼 수 있어야 한다.

2. 점수 계산 규칙
- 체스 프로그램에서 현재까지 남아 있는 말에 따라 점수를 계산할 수 있어야 한다.
- 각 말의 점수는 queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.
- pawn의 기본 점수는 1점이다. 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.
- king은 잡히는 경우 경기가 끝나기 때문에 점수가 없다.
- 한 번에 한 쪽의 점수만을 계산해야 한다.

### **추가되는 클래스들**

### chessBoard 패키지
- [ ] BoardStatus
  - ColorBoard 에 Color 별의 Piece 를 넣어주는 역할을 한다.
  - WhiteBoard, BlackBoard의 점수를 보고 결과를 출력한다.
  - [ ] ColorBoard
    - ColorBoard 의 점수를 계산한다.
- [ ] ColorResult
  - 해당 Color 의 승패 결과를 나타낸다. 
- [ ] ColorScore
  - 해당 Color 의 점수를 나타낸다.
- [ ] GameResult
  - 승, 무, 패를 나타내는 Enum Type 객체
