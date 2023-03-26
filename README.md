# 기능목록

## 1단계

### 컨트롤러

- LoginSession
    - [x] 현재 로그인 되었는지 알 수 잇다.
    - [x] 로그인 할 수 있다.
    - [x] 로그아웃 할 수 있다.

- GameCommand
    - [x] 게임 시작 커맨드는 "start"다.
    - [x] 게임 종료 커맨드는 "end"다.
    - [x] 게임 결과 계산 커맨드는 "status"다.
    - [x] 체스 기물 이동 요청 커맨드는 "move 시작위치 이동위치" 이다.
        - [x] 위치의 첫 번째 인자는 a~h까지 가능하다.
        - [x] 위치의 두 번째 인자는 1~8까지 가능하다.

- Request
    - [x] 게임 커맨드와 요청 데이터를 가지고있다.

- Response
    - [x] 응답은 해당 응답이 어떤 행위에 대한 응답인지 포함한다.
        - [x] start는 시작 명령에 대한 응답이다.
        - [x] move는 이동 명령에 대한 응답이다.
        - [x] end는 종료 명령에 대한 응답이다.
        - [x] status는 게임 결과 출력에 대한 응답이다.
        - [x] fail은 해당 응답이 실패했다는 것이다.

- StartController
    - [x] 보드 세션에 보드를 생성해서 등록한다.
    - [실패] 세션에 이미 보드가 있다면 해당 요청은 실패한다.

- MoveController
    - [x] 세션에 있는 보드에 대한 이동 명령을 수행한다.
    - [실패] 세션에 보드가 없으면 실패한다.

- EndController
    - [x] 게임 종료에 대한 명령을 수행한다.

- StatusController
    - [x] 게임 결과 반환에 대한 명령을 수행한다.

### 도메인

#### Piece

- InitPawnPiece
    - [x] 해당 위치에 아무 말이 없다면 앞으로 두 칸 갈 수 있다.
    - [x] 해당 위치에 상대편 말이 있다면 대각 방향으로 한 칸 이동할 수 있다.
    - [x] InitPawnPiece 는 이동 후 PawnPiece 로 바뀐다.

- PawnPiece
    - [x] 해당 위치에 아무 말이 없다면 앞으로 한 칸 갈 수 있다.
    - [x] 해당 위치에 상대편 말이 있다면 대각 방향으로 한 칸 이동할 수 있다.
    - [x] 해당 기물은 이동 후 변경되지 않는다.

- KnightPiece
    - [x] 해당 위치에 같은 색의 기물이 없으면 앞으로 한 칸 이동한 후 같은 방향 대각으로 한 칸 이동할 수 있다.
    - [x] 다른 piece 가 중간에 있더라도 뛰어넘을 수 있다
    - [x] 해당 기물은 이동 후 변경되지 않는다


- BishopPiece
    - [x] 해당 위치에 같은 색의 기물이 없으면 대각 방향으로 원하는 만큼 이동할 수 있다.
    - [x] 해당 기물은 이동 후 변경되지 않는다.

- RookPiece
    - [x] 해당 위치에 같은 색의 기물이 없으면 직선 방향으로 원하는 만큼 이동할 수 있다.
    - [x] 해당 기물은 이동 후 변경되지 않는다.

- QueenPiece
    - [x] 해당 위치에 같은 색의 기물이 없으면 직선 방향으로 원하는 만큼 이동할 수 있다.
    - [x] 해당 위치에 같은 색의 기물이 없으면 대각 방향으로 원하는 만큼 이동할 수 있다.
    - [x] 해당 기물은 이동 후 변경되지 않는다.

- KingPiece
    - [x] 직선 혹은 대각 방향으로 한 칸 이동할 수 있다.
    - [x] 해당 기물은 이동 후 변경되지 않는다.

#### Position

- [x] File 과 Rank 를 갖는다.
- [x] 다른 포지션과의 Rank 순서 차이를 구할 수 있다.
- [x] 다른 포지션과의 File 순서 차이를 구할 수 있다.
- [x] 다른 포지션과의 직선 경로 Position 을 얻을 수 있다.
    - [예외] 직선이 아니면 예외가 발생한다.

#### Board

- [x] Piece 들의 위치를 초기화 한다.
- [x] 보드는 자신의 판 정보를 반환할 수 있다.
- [x] 시작 Position 과 목표 Position 를 입력하면 Piece 를 움직일 수 있다.
    - [예외] 중간 경로에 다른 말이 있으면 예외가 발생한다.
    - [예외] 해당 위치로 말이 이동할 수 없다면 예외가 발생한다.
    - [x] 이동 명령은 흰색 부터 번갈아 가면서 내릴 수 있다.

#### Game

- [x] Game 에 이동 명령을 내리면 이동 명령을 수행 받은 Game 을 반환한다.
    - [x] 만약 두 진영 Score 가 0인 진영이 있다면 게임을 종료된 게임을 반환한다.
        - [x] 한 진영의 기물이 없으면 0점이다.
        - [x] 한 진영에 왕이 없으면 해당 진영은 0점이다.
        - [x] 왕 혼자 있더라도 해당 진영은 0점이다.

#### Score

- [x] 보드 데이터와 색을 통해 점수를 계산한다.
    - [x] 왕이 죽었으면 점수는 0점이다.
    - [x] 왕은 점수 갖지않는다.
    - [x] Queen 은 9점으로 계산한다.
    - [x] Rook 은 5점으로 계산한다.
    - [x] bishop 은 3점으로 계산한다.
    - [x] Knight 는 2.5점으로 계산한다.
    - [x] Pawn 은 1점으로 계산한다.
        - [x] 같은 세로줄에 있는 폰은 0.5점으로 계산한다.

#### BoardSession

- [x] 현재 시스템에서 돌아가고 있는 보드를 관리한다.
- [x] 보드가 존재하는지 확인한다.
- [x] 보드를 세션에 등록한다.

### 뷰

#### InputView

- [x] start 와 end 를 입력 받는다.

#### OutputView

- [x] 체스판을 출력한다.

### Database

use chess;

CREATE TABLE piece (
rank_id VARCHAR(255) NOT NULL,
file_id VARCHAR(255) NOT NULL,
pieceType VARCHAR(255) NOT NULL,
color VARCHAR(255) NOT NULL,
game_id VARCHAR(255) NOT NULL
);

CREATE TABLE user (
user_id VARCHAR(255) NOT NULL,
user_password VARCHAR(255) NOT NULL,
user_name VARCHAR(255) NOT NULL
);

CREATE TABLE game (
user_id VARCHAR(255) NOT NULL,
game_id VARCHAR(255) NOT NULL,
turn VARCHAR(255) NOT NULL
);

#### Dao

- UserDao
    - [x] 새로운 유저를 등록한다. (Create)
        - [x] 같은 id 혹은 닉네임이 있으면 생성하지 못한다.
    - [x] id와 비밀번호로 유저 이름를 가져온다. (Read)
        - [x] id와 비밀번호가 둘 다 맞지 않는다면 정보를 가져올 수 없다.

- GameDao
    - [x] user_id 를 기반으로 게임을 등록한다. (Create)
        - [x] 이미 user_id 에 대한 게임이 있다면 등록할 수 없다.
    - [x] 유저의 아이디로 게임 아이디를 가져온다. (Read)
        - [x] 해당 유저의 게임이 없다면 해당 테이블의 게임은 없다.
    - [x] 게임 id 를 기반으로 게임을 삭제한다. (Delete)

- PieceDao
    - [X] game_id 를 통해 해당 게임의 piece 를 통해 Board 데이터를 가져온다. (Read)
    - [x] game_id 를 통해 해당 game_id의 피스를 삭제한다 (Delete)
