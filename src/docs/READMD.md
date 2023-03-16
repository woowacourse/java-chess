## 1단계 기능 요구 사항

- [x] 게임 시작 메세지 출력
- [x] 커맨드 입력 메세지 출력
- [x] 커맨드 입력 기능
- [x] 체스판 출력
- [x] 체스판 만드는 기능

### 기물

- [x] 위치를 갖는다.
- [x] 종류는 킹, 퀸, 룩, 나이트, 비숍, 폰 이 있다.

### 체스 판

- [x] 가로는 Rank 이다.
- [x] 세로는 File 이다.
- [x] 8 X 8 이다.




```mermaid
classDiagram
  class ChessBoard {
    -List~Piece~ pieces
  }
  
  class ChessBoardFactory
  
  class Piece
  <<Abstract>> Piece
  
  class Color
  <<Enumeration>> Color
  
  class Direction
  <<Enumeration>> Direction
  
  ChessController ..> ChessBoard
  ChessController ..> ChessBoardFactory
  ChessController ..> ChessState
  
  ChessBoard --> Piece
  ChessBoard ..> PiecePosition
  ChessBoard ..> WayPoints
  ChessBoard ..> Turn
  
  Piece --> Color
  Piece --> PiecePosition
  Piece ..> Path

  
  Path --> PiecePosition
  WayPoints --> PiecePosition
  
  PiecePosition --> Rank
  PiecePosition --> File
  PiecePosition ..> Direction
  
  ChessBoardFactory ..> ChessBoard : create
```

### Piece 추상 클래스
```mermaid
classDiagram
  class Piece
  <<Abstract>> Piece
  
  Piece <|-- King
  Piece <|-- Queen
  Piece <|-- Bishop
  Piece <|-- Rook
  Piece <|-- Knight
  Piece <|-- Pawn
```

### 상태 패턴
```mermaid
classDiagram
  class ChessState
  <<Interface>> ChessState
  
  class AbstractChessState
  <<Abstract>> AbstractChessState
  
  ChessState ..> Command
  AbstractChessState --> ChessBoard
  ChessState <|.. AbstractChessState
  AbstractChessState <|-- Initialize
  AbstractChessState <|-- Running
  AbstractChessState <|-- End
  Command --> Type
```

---
