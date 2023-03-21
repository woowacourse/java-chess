### 기능목록

#### 도메인 기능 목록

- 체스말 (Piece)
    - [x] 위치를 갖는다.
        - [x] 위치는 File, Rank로 구성된다.
    - [x] 총 6가지의 종류가 있다
        - King, Queen, Knight, Bishop, Rock, Pawn
    - [x] 흑 혹은 백의 색깔을 갖는다.
    - [x] 각 색깔 당 16개의 체스말을 갖는다.
        - King(1개), Queen(1개), Knight(2갸), Bishop(2개), Rock(2개), Pawn(8개)

- 체스보드 (Board)
    - [x] 초기에 체스말을 갖는다.
    - [x] 체스보드의 말이 이동한다.
        - [x] 입력 받은 현재 위치에 체스말이 있는지 확인한다.
            - [x] 말이 없으면 에외가 발생한다.
        - [x] 목표 위치까지 경로에 다른 체스말이 있는지 확인한다.
            - [x] 말이 있으면 예외가 발생한다.
        - [x] 체스말을 이동시킨다.
            - [x] 잡을 수 있는 말이 있으면 잡는다.
            - [x] 잡을 수 없는 말이 있으면 예외가 발생한다.
    - [x] 8 x 8 의 칸을 갖는다.
        - 가로 칸은 8개의 Rank로 구성된다. (1~8)
        - 세로 칸은 8개의 File로 구성된다. (a~h)

- 말 이동
- [x] Knight 을 제외한 모든 말은 다른 기물을 뛰어넘을 수 없다.
    - King
        - [x] 전후좌우, 대각선 방향으로 한 칸 움직일 수 있다.
    - Queen
        - [x] 전후좌우, 대각선 방향으로 여러 칸 움직일 수 있다.
    - Bishop
        - [x] 대각선 방향으로 여러 칸 움직일 수 있다.
    - Knight
        - [x] 두 칸 전진한 상태에서 좌우로 한 칸 움직일 수 있다.
        - [x] 다른 기물을 뛰어넘을 수 있다.
    - Rook
        - [x] 전후좌우 방향으로 여러 칸 움직일 수 있다.
    - Pawn
        - [x] 초기 상태에서 한 칸 혹은 두 칸 전진할 수 있다.
        - [x] 초기 상태가 아닌 경우, 한 칸만 전진할 수 있다.
        - [x] 후퇴할 수 없다.
        - [x] 전진해서 상대방 기물을 잡을 수 없다.
        - [x] 대각선 한 칸 이동해서 상대방 기물을 잡을 수 있다.

- 체스게임 (ChessGame)
    - [x] 입력 받은 위치의 말이 이동할 순서인지 확인한다.
        - [x] 이동할 순서가 아니면 예외가 발생한다.
    - [x] 말을 이동시킨다.
    - [x] 말이 이동하면, 다음 턴에 이동할 색을 변경한다.

- 게임 명령어 (GameCommand)
    - [x] start: 게임 시작
    - [x] end: 게임 종료
    - [x] move: 말 이동

#### 뷰 기능 목록

#### 입력

- 게임 (제어) 명령어를 입력받는다.
    - move 다음 공백으로 구분하여 출발 위치와 도착 위치 입력

#### 출력

- [x] 게임 시작 안내 메시지를 출력한다
- [x] 체스판을 출력한다

```
체스 게임을 시작합니다.
게임 시작은 start, 종료는 end 명령을 입력하세요.
```

- 게임이 시작된 경우 체스보드와 체스말을 출력한다.

```
RNBQKBNR
PPPPPPPP
........
........
........
........
pppppppp
rnbqkbnr
```

### 리팩토링 정리

ChessGame

- [ ] ChessGame 생성 책임 이동
    - ChessGame을 사용하는 곳에서 인스턴스화를 진행하도록 책임 이전
- [ ] ChessGame을 값 객체로 만드는 것 생각하기

Controller

- [ ] ChessGame을 생성자의 인자로 받는 것 고민하기
- [ ] while 반복문과 조건절 수정하기
- [ ] processStartCommand 메서드 사용 시 타입 활용으로 변경하기
- [ ] 도메인의 표준타입 정의하기
- [ ] 재입력 로직 추가
- [ ] GameCommand 고민하기

Board

- [x] PiecesGenerator 네이밍 수정
    - factory 를 만들어 보기

기타

- [ ] Readme 기능 목록 체크
- [ ] Color 클래스에서 isBlack 메서드 삭제하기
- [x] File, Rank 클래스에서 index 상태를 ordinal()로 수정

test

- [ ] test에서 given, when, then 사용하기
- [ ] test에서 inner class Nested
- [ ] test에서 Fixture 활용해보기
- [ ] test 메서드 네이밍 일관성 지키기

