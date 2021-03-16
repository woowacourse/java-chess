## 기능 목록

- [ ] Piece
  - [ ] Rook
  - [ ] Knight
  - [ ] Bishop
  - [ ] Queen
  - [ ] King
  - [ ] Pawn
  - [ ] 자신의 position 을 가지고 있는다.
  - [ ] 자신의 초기 위치를 세팅한다.
  - [ ] 입력된 좌표로 이동한다.
    - [ ] 입력된 좌표로 이동할수 있는지 확인한다.

- [ ] ChessBoard
  - [ ] 체스판 좌표에 Piece 존재 여부를 확인한다.

- [x] Position
  - [x] 좌표 x, y 값을 가지고 있는다.
  - [x] 들어온 좌표가 0보다 작거나 7보다 크면 예외가 발생한다.

- [x] ROW (행) `->` ENUM
  - [x] a -> h 를 0 -> 7 으로 치환

- [x] COL (열) `->` ENUM
  - [x] 1 -> 8 를 7 -> 0 으로 치환
    
- [ ] `ROW SETTER ?` 미정
  - [ ] Piece 의 타입별로 초기 ROW 좌표를 정해준다.