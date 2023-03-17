## 체스 게임 기능 목록
### 도메인 기능

- ChessGame: 체스 실행 로직 도메인
  - [ ] 게임을 시작한다.
  - [ ] 게임을 종료한다.
  - [ ] 턴을 넘긴다.

- Board: 체스판
  - [x] 체스판을 세팅한다.
  - [ ] 이동한다.

- BoardMaker: 체스판 생성기
  - [x] 체스판을 만든다.

- Square: 한칸 (Rank와 File의 조합)
  - [x] 같은 File인지 확인한다.
  - [x] 같은 Rank인지 확인한다.
  - [x] 같은 칸인지 확인한다.
  - [x] 이동한 칸을 알려준다.
  - [x] 한 칸 차이가 나는지 확인한다.

- Rank, File: 행, 렬에 관한 Enum

- PieceDirection: 체스 말이 갈 수 있는 방향을 저장하는 enum
  - [x] 체스 말의 이동 방향을 알려준다.

- Piece: 체스말
  - [x] 적인지 알려준다.
  - [ ] 이동가능한지 확인한다.
  - [ ] 이동한다.

  - Pawn: 폰
    - PawnMoveStrategy
  - Bishop: 비숍
    - DiagonalMoveStrategy
  - Knight: 나이트
    - KnightMoveStrategy
  - Rook: 룩
    - StraightMoveStrategy
  - Queen: 퀸
    - DiagonalMoveStrategy
    - StraightMoveStrategy
  - King: 킹
    - OneSquareMoveStrategy

- Color: 체스말의 색상에 관한 Enum

- MoveStrategy: 이동 전략
  - [ ] 이동가능한지 확인한다.
  - [ ] 이동한다.

  - DiagonalMoveStrategy: 대각 이동 전략
    - [ ] 검색 좌표가 대각에 위치하는지 확인한다.
    - [ ] 대각으로 이동한다.

  - StraightMoveStrategy: 직선 이동 전략
    - [ ] 검색 좌표가 직선에 위치하는지 확인한다.
    - [ ] 직선으로 이동한다.

  - KnightMoveStrategy: 나이트 이동 전략
    - [ ] 검색 좌표가 나이트가 이동 가능한 위치인지 확인한다.
    - [ ] 나이트 방식으로 이동한다.

  - OneSquareMoveStrategy: 한 칸 이동 전략
    - [ ] 검색 좌표가 인접해있는지 확인한다.
    - [ ] 한 칸 이동한다.

  - PawnMoveStrategy: 폰 이동 전략
    - [ ] 검색 좌표가 폰이 이동 가능한 위치인지 확인한다.
    - [ ] 폰 방식으로 이동한다.

### UI

- OutputView
  - [ ] 게임 시작 메시지 출력 기능
  - [ ] 게임 커멘드 설명 메시지 출력 기능
  - [ ] 현재 게임 상태 출력 기능

- InputView
  - 명령어 입력 기능
    - [ ] 게임 시작 커멘드 입력 기능
    - [ ] 게임 종료 커멘드 입력 기능
    - [ ] 말 이동 커멘드 입력 기능
