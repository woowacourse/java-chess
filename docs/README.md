# 기능 목록

## ChessBoard

- [] Square를 가지는 List를 필드로 가진다
- [] 초기 맵을 설정한다

## Rank

- [x] ChessBoard의 1부터 8까지의 행을 나타낸다
- [x] 인덱스의 오름차순으로 정렬한 List<Rank>를 반환한다
- [x] 입력한 값이 Rank에 존재하는 Value인지 유효성 검사

## Column

- [x] ChessBoard의 a부터 h까지의 열을 나타낸다
- [x] 인덱스의 오름차순으로 정렬한 List<Column>를 반환한다
- [x] 입력한 값이 Column에 존재하는 Value인지 유효성 검사

## Square

- [] ChessBoard의 한 칸에 해당한다
- [x] Index, Piece, name(ex. a7)을 필드로 가진다

## Position

- [x] Rank와 Column을 필드로 가진다

### BlackPlayer

- [x] COLOR로 Black을 가진다
- [x] 1열과 2열에 해당하는 Rank를 가진다(8,7)

### WhitePlayer

- [x] COLOR로 White를 가진다
- [] 1열과 2열에 해당하는 Rank를 가진다(1,2)

## PieceGenerator

- [x] 상수로 각 기물이 몇개 생성해야하는지 가지고 있다
- [] 첫번째 열 기물들을 생성해 반환한다 (Rook, Knight, Bishop ... )
- [x] 두번째 열 기물들을 생성해 반환한다 (Pawn * 8)

## Piece (abstract)

- [x] 상수로 COLOR를 필드로 가진다 (White/Black)
- [ ] COLOR에 따라 출력값을 결정한다(ex.P/p)
- [x] Piece 종류에따라 움직일 수 있다(move method)

## InitialPositions 
- [x] 기물들의 초기 위치 값을 나타낸다
- [x] List<Column>을 일급컬렉션으로 가진다

### Pawn (Piece)

- [x] Pawn의 초기 위치값(a-h)을 채운다
- todo: move override , rank와 column를 필드로 가진다

### Rook (Piece)

- [x] Rook의 초기 위치값(a,h)을 채운다
- todo: move override , rank와 column를 필드로 가진다

### Knight (Piece)

- [x] Knight의 초기 위치값(b,g)을 채운다
- todo: move override , rank와 column를 필드로 가진다

### Bishop (Piece)

- [x] Bishop의 초기 위치값(c,f)을 채운다
- todo: move override , rank와 column를 필드로 가진다

### Queen (Piece)

- [x] Queen의 초기 위치값(d)을 채운다
- todo: move override , rank와 column를 필드로 가진다

### King (Piece)

- [x] King의 초기 위치값(e)을 채운다
- todo: move override , rank와 column를 필드로 가진다

