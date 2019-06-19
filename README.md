# java-chess
체스 게임 구현을 위한 저장소

## 기능 구현 목록

### 도메인 모델
- 각 축의 좌표 구현
    - ~~XPosition~~
    - YPosition
- 체스말의 위치를 나타내는 Point 구현
- 체스말의 타입을 나타내는 열거형 ChessPieceType 구현
- 체스말의 색상을 나타내는 열거형 ChessColor 구현
- 체스말의 추상클래스 ChessPiece 구현
- 체스말의 구현클래스 구현
    - King
    - Queen
    - Rook
    - Bishop
    - Knight
    - Pawn
- 체스말을 포함하는 ChessBoard 구현
- 체스 로직을 담당하는 ChessEngine 구현
