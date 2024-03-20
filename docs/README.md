## 도메인 용어 정리

- 체스 게임(Chess Game)
- 체스판(Board)
- 체스판 행(Rank)
- 체스판 열(File)
- 체스판 좌표(Coordinate)
- 기물(Piece)
- 기물 종류(Piece Type)
    - King, Queen, Bishop, Rook, Knight, Pawn
- 진영(Team)

## 요구 사항

### 체스판

- [x] 체스판을 생성할 수 있다.
    - [x] 체스판에는 좌표가 있다.
- [x] 체스판에는 기물이 있다.
    - [x] White King = E 1
    - [x] White Queen = D 1
    - [x] White Bishop = C 1, F 1
    - [x] White Knight = B 1, G 1
    - [x] White Rook = A 1, H 1
    - [x] White Pawn = (A - H)2
    - [x] Black King = E 8
    - [x] Black Queen = D 8
    - [x] Black Bishop = C 8, F 8
    - [X] Black Knight = B 8, G 8
    - [x] Black Rook = A 8, H 8
    - [X] Black Pawn = (A - H)7

### 진영

- [x] 진영을 생성할 수 있다.
    - [x] 진영은 흑과 백이 있다.

### 체스판 좌표

- [x] 좌표를 생성할 수 있다.
    - [x] 좌표는 행,열을 가진다.

### 체스판 행

- [x] 행을 생성할 수 있다.
    - [x] 행은 1~8까지의 값만 허용한다.

### 체스판 열

- [x] 열을 생성할 수 있다.
    - [x] 열은 소문자 a부터 h까지의 값만 허용한다.

### 기물

- [x] 기물은 진영에 소속 된다.
- [x] 기물은 종류가 있다.

### 기물 종류

- [x] 기물 종류를 생성할 수 있다.
    - [x] King
    - [x] Bishop
    - [X] Rook
    - [x] Queen
    - [x] Pawn
    - [x] Knight

### 입출력

- [x] 시작과 종료를 입력받을 수 있다.
- [x] 체스판을 출력할 수 있다.
    - [x] 흑팀은 대문자로 출력한다.
    - [x] 백팀은 소문자로 출력한다.
