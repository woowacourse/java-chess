## 체스 게임 기능 목록

### 데이터베이스 생성 쿼리

```agsl
CREATE TABLE User (
	user_id VARCHAR(20) NOT NULL,
	nickname VARCHAR(20) NOT NULL,
	PRIMARY KEY (user_id)
)
```

```agsl
CREATE TABLE Game (
	game_id BIGINT NOT NULL AUTO_INCREMENT,
	user_id VARCHAR(20) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (game_id),
	FOREIGN KEY (user_id) REFERENCES User(user_id)
)
```

```agsl
CREATE TABLE Board (
	game_id BIGINT NOT NULL,
	turn INT NOT NULL,
	piece_file CHAR(1) NOT NULL,
	piece_rank CHAR(1) NOT NULL,
	piece_type VARCHAR(10) NOT NULL,
	piece_team TINYINT NOT NULL,
	PRIMARY KEY (game_id, turn, piece_file, piece_rank),
	FOREIGN KEY (game_id) REFERENCES Game(game_id)
)
```


### 도메인 기능

- ChessGame: 체스 게임
  - [x] 턴을 관리한다.
  - [x] 승자를 판단한다.

- Board: 체스판
  - [x] 체스판을 세팅한다.
  - [x] 이동한다.
  - [x] 남아 있는 말에 대한 점수를 구한다.
    - Queen 9점, Rook 5점, Bishop 3점, Knight 2.5
    - Pawn 1점
      - 같은 세로줄에 같은 색 Pawn이 있으면 0.5
    - King 0점
    - 한 번에 한 쪽의 점수만을 계산한다.

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
  - [x] 이동 방향을 알려준다.
    - 이동이 불가능하면 예외가 발생한다.
  - [x] 폰인지 알려준다.
  - [x] 킹인지 알려준다.

  - Pawn: 폰
    - `PieceDirection.WHITE_PAWN` & `PieceDirection.WHITE_PAWN`
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

### UI

- OutputView
  - [x] 게임 시작 메시지 출력 기능
  - [x] 게임 커멘드 설명 메시지 출력 기능
  - [x] 현재 게임 상태 출력 기능
  - [x] 각 진영 점수 출력 기능

- InputView
  - 명령어 입력 기능
    - [x] 게임 시작 커멘드 입력 기능
    - [x] 게임 종료 커멘드 입력 기능
    - [x] 말 이동 커멘드 입력 기능
    - [x] 상태 확인 입력 기능
