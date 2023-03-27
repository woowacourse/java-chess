## 체스 게임 기능 목록
### 도메인 기능

- ChessGame: 체스 게임 진행을 위한 도메인
  - [x] 게임을 시작한다.
  - [x] 말을 이동한다.
  - [x] 게임을 재시작한다.
  - [x] 게임이 끝났는지 확인한다.

- Board: 체스판
  - [x] 체스판을 세팅한다.
  - [x] 이동한다.
  - [x] King이 잡혔는지 확인한다.
  - [x] 현재 남아있는 말들의 점수를 계산한다.
  - [x] 같은 File에 같은 색의 Pawn이 있는지 확인한다.

- BoardFactory: 체스판 생성기
  - [x] 체스판을 만든다.

- Score: 점수
  - [x] 점수를 더한다.
  - [x] 점수를 뺀다.

- ScoreCalculator: 점수 계산기
  - [x] 백팀의 점수를 계산한다.
  - [x] 흑팀의 점수를 계산한다.

- Square: 한칸 (Rank와 File의 조합)
  - [x] 같은 File인지 확인한다.
  - [x] 같은 Rank인지 확인한다.
  - [x] 같은 칸인지 확인한다.
  - [x] 이동한 칸을 알려준다.
  - [x] 한 칸 차이가 나는지 확인한다.

- Rank, File: 행, 렬에 관한 Enum

- Direction: 방향 정보를 담고 있는 enum

- PieceDirection: 체스 말이 갈 수 있는 방향을 저장하는 enum. 기물 마다의 Direction들을 저장하고 있는 List를 들고 있음.
  - [x] 체스 말의 이동 방향을 알려준다.

- Piece: 체스말
  - [x] 적인지 알려준다.
  - [x] 이동 방향을 알려준다.
    - 이동이 불가능하면 예외가 발생한다.
  - [x] 폰인지 알려준다.

  - WhitePawn: 백폰
    - `PieceDirection.WHITE_PAWN`
  - BlackPawn: 흑폰
    - `PieceDirection.BLACK_PAWN`
  - Bishop: 비숍
    - `PieceDirection.DIAGONAL`
  - Knight: 나이트
    - `PieceDirection.KNIGHT`
  - Rook: 룩
    - `PieceDirection.STRAIGHT`
  - Queen: 퀸
    - `PieceDirection.KING_AND_QUEEN`
  - King: 킹
    - `PieceDirection.KING_AND_QUEEN`

- Color: 체스말의 색상에 관한 Enum
- PieceType: 체스 말의 종류에 관한 Enum

- Turn: 차례 관련 객체
  - [x] 첫 차례는 백팀의 차례이다.
  - [x] 백팀 다음 차례는 흑팀이다.
  - [x] 흑팀 다음 차례는 백팀이다.

### UI

- OutputView
  - [x] 게임 시작 메시지 출력 기능
  - [x] 게임 커멘드 설명 메시지 출력 기능
  - [x] 현재 게임 상태 출력 기능

- InputView
  - 명령어 입력 기능 (Command enum이 진행)
    - [x] 게임 시작 커멘드 입력 기능
    - [x] 게임 종료 커멘드 입력 기능
    - [x] 말 이동 커멘드 입력 기능
    - [x] 현재 상태 커멘드 입력 기능

### DB

- Board
  - [x] board id로 검색 기능
  - [ ] board 저장 기능
