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