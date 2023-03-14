# 기능 목록

## ChessBoard
- [] Square를 가지는 List를 필드로 가진다 

## Rank 
- [x] ChessBoard의 1부터 8까지의 행을 나타낸다

## Column
- [] ChessBoard의 a부터 h까지의 열을 나타낸다

## Square
- [] ChessBoard의 한 칸에 해당하고
- [] Rank와 Column, Piece을 필드로 가진다

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

