# 기능 목록

## ChessBoard

- [] Square를 가지는 List를 필드로 가진다

## Rank

- [x] ChessBoard의 1부터 8까지의 행을 나타낸다
- [x] 인덱스의 오름차순으로 정렬한 List<Rank>를 반환한다
- [x] 입력한 값이 Rank에 존재하는 Value인지 유효성 검사

## Column

- [x] ChessBoard의 a부터 h까지의 열을 나타낸다
- [x] 인덱스의 오름차순으로 정렬한 List<Column>를 반환한다
- [x] 입력한 값이 Column에 존재하는 Value인지 유효성 검사
-

## Square

- [] ChessBoard의 한 칸에 해당한다
- [x] Index, Piece, name(ex. a7)을 필드로 가진다

## Position

- [x] Rank와 Column을 필드로 가진다

## Piece (abstract)

- [] Color를 필드로 가진다 (White/Black)
- [] Piece 종류에따라 움직일 수 있다(move method)
- TODO: 이동시 위치값을 어떻게 할지 고민한다
    - rank와 column를 필드로 가진다

### Pawn (Piece)

- todo: move override , rank와 column를 필드로 가진다

### Rook (Piece)

- todo: move override , rank와 column를 필드로 가진다

### Bishop (Piece)

- todo: move override , rank와 column를 필드로 가진다

### Knight (Piece)

- todo: move override , rank와 column를 필드로 가진다

### Queen (Piece)

- todo: move override , rank와 column를 필드로 가진다

### King (Piece)

- todo: move override , rank와 column를 필드로 가진다

