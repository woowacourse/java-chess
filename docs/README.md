# 기능 목록

## BoardDto

- [x] 생성시, Map<Position, Piece>를 인자로 받는다
- [x] List<List<string>> 형태의 dto를 만든다
- [x] getter로 위의 형태를 반환한다

## ChessGame

- [] king이 잡히는 경우 게임 종료
- [x] 필드로 ChessBoard를 가진다
- [x] ChessBoard의 Map<Position, Piece>를 반환한다

## ScoreCalculator

- [] 현재 남아 있는 말에 대한 점수를 구한다

## ChessBoard

- [x] Map<Position,Piece>를 필드로 가진다
- [x] 방어적 복사를 통해 필드를 반환한다

## BoardStrategy

- [x] 보드를 생성하는 인터페이스이다

## InitialBoardStrategy

- [x] BoardStrategy를 구현한다
- [x] 체스 보드의 기물들을 초기화 한다 (블랙 따로, 화이트 따로 초기화)

## Rank

- [x] ChessBoard의 1부터 8까지의 행을 나타낸다
- [x] 인덱스는 1-8까지 가진다
- [x] 인덱스의 오름차순으로 정렬한 List<Rank>를 반환한다
- [x] 입력한 값이 Rank에 존재하는 Value인지 유효성 검사

## Column

- [x] ChessBoard의 a부터 h까지의 열을 나타낸다
- [x] 인덱스는 1-8까지 가진다
- [x] 인덱스의 오름차순으로 정렬한 List<Column>를 반환한다
- [x] 입력한 값이 Column에 존재하는 Value인지 유효성 검사

## Position

- [x] Column과 Rank를 필드로 가진다

## Name

- [x] 각 기물들의 이름을 enum으로 관리한다.

## Color

- [x] Black, White, None

## Piece (abstract)

- [x] 상수로 COLOR를 필드로 가진다. (White/Black)
- [x] 상수로 NAME을 필드로 가진다.
- [x] COLOR에 따라 이름의 출력값을 결정한다(ex.P/p)
- [x] Piece 종류에따라 움직일 수 있다(move method)

### EmptyPiece (Piece)

- [x] .을 이름으로 갖는다
- [x] 오버라이드한 move 메서드는 아무 수행도 하지 않는다.

### Pawn (Piece)

- [x] p를 이름으로 갖는다
- [x] move override , rank와 column를 필드로 가진다
- [x] 상수로 점수(1점)를 가진다
    - [] 같은 세로줄에 같은 색의 Pawn이 있는 경우 0.5점을 가진다

### Rook (Piece)

- [x] r를 이름으로 갖는다
- [x] move override , rank와 column를 필드로 가진다
- [] 상수로 점수(5점)를 가진다

### Knight (Piece)

- [x] n를 이름으로 갖는다
- [x] move override , rank와 column를 필드로 가진다
- [x] 상수로 점수(2.5점)를 가진다

### Bishop (Piece)

- [x] b를 이름으로 갖는다
- [x] move override , rank와 column를 필드로 가진다
- [x] 상수로 점수(3점)를 가진다

### Queen (Piece)

- [x] q를 이름으로 갖는다
- [x] move override , rank와 column를 필드로 가진다
- [x] 상수로 점수(9점)를 가진다

### King (Piece)

- [x] k를 이름으로 갖는다
- [x] move override , rank와 column를 필드로 가진다
- [x] 상수로 점수(0점)를 가진다

## PieceGenerator

- [x] 상수로 각 기물이 몇개 생성해야하는지 가지고 있다
- [x] 첫번째 열 기물들을 생성해 반환한다 (Rook, Knight, Bishop ... )
- [x] 두번째 열 기물들을 생성해 반환한다 (Pawn * 8)~~


- start, end 입력
- [x] 시작 위치의 piece 존재 유무 확인 map.contains(Position)
    - [x] 있으면 가져와서, piece 변수 저장
- [x] gap 구하기
- [x] gap의 abs 를 piece에 넣어서 한 번에 이동 가능한지 확인하기
    - [x] 폰(movableDistance 1), 킹(movableDistance 1), 나이트(movableDistance 1.2 / 2.1)
    - [x] 정나머지는 true 반환
- [x] start -end = gap 으로 direction 정하기
- [x] 기물이 해당 direction으로 이동할 수 있는지 구하기
- [x] 현재 위치가 end가 될때까지, start에 direction의 단위 위치를 더하면서, 해당 포지션에 있는 기물이 존재하는지 확인한다
    - [x] 이동하는 경로에 기물이 존재하면, 바로 break
    - [x] 끝에 도달했는데 기물이 있는 경우, 색깔이 같은 편이면 break
    - [x] 끝에 도달했는데 기물이 있는경우, 색깔이 다른편인 경우만 ok
- [x] 모든 조건을 충족하면 이동!
