## 체스 기능 요구 사항

### 1단계 체스판 초기화
- 체스 게임 시작 문장 출력
- "start"를 입력 받는다.
- "end"를 입력받는다.
  - 이외의 입력이 들어오면 예외

- 체스판을 초기화한다.

### 객체 설계
- Board
  - Piece
    - Color(Black, White)
    - Position(row, column)
    - King, Queen, Knight, Bishop, Rook, Pawn
- PieceInitializer

- InputView
- OutputView

### 말 이동
- Position
  - 이동
    - 8x8 검증
- Piece
  - 자기가 갈 수 있는 범위를 확인
- Board
  - 이 말을 여기서 저기로 이동시켜라
    - 다른 말이 있는지 확인