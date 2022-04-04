# java-chess

> 체스 미션 저장소 ♔♕♖♗♘♙

## 기능 목록

#### 1단계 - 체스판 초기화
- [x] 콘솔을 이용해 체스 게임을 진행할 수 있다.
- [x] Start : 체스판을 초기화한다. 
  - [x] 게임을 시작하기 전에만 Start할 수 있다. 
- [x] End : 체스 게임을 종료한다.

#### 2단계 - 말 이동
- [x] Move : 이동 시키려는 기물의 위치(Source)와 목적 위치(Target)를 이용해 기물을 이동시킨다. 
  - [x] 행마법에 맞아야만 이동이 가능하다. 
  - [x] 이동에 성공해야만 상대 턴으로 넘어간다.
  - [x] 행마법에 맞는 이동이며, 목적 위치의 기물이 상대의 King일 경우 게임을 종료한다.

#### 3단계 - 승패 및 점수
- [x] Status : 각 진영(White/Black)의 점수와 승패를 구한다.
  - [x] 승패의 경우 현재 턴을 기준으로 구한다.
  - [x] 해당 기능을 사용해도 상대 턴으로 넘어가지는 않는다. 
  
#### 4단계 - 웹 UI 적용
- [x] 웹을 이용해 체스 게임을 진행할 수 있다.
- [X] Start, End, Status는 버튼으로, Move는 클릭으로 구현한다.
  - [x] 웹에서는 게임이 종료된 후에 Start를 누르면 게임이 재시작된다.
- [x] Move를 하고 나면, 체스판을 다시 출력한다. 
- [x] 게임 상의 오류 메시지를 웹으로 전송해 출력한다.

#### 5단계 - DB 적용
- [ ] 체스 진행 정보를 데이터베이스에 저장하고 불러 올 수 있다. 
  - [x] 데이터베이스(MySQL)에 연결한다.
- [ ] 서버를 재시작하더라도 이전에 하던 체스 게임을 이어서 진행할 수 있다. 

#### 더 해보기
- [ ] 체스 게임방을 만들 수 있다. 
- [ ] 체스 게임방에 입장할 수 있다. 
- [ ] 사용자별로 체스 게임 기록을 관리할 수 있다.

## 요구 사항
- 웹 UI를 적용할 때 도메인 객체의 변경을 최소화해야한다.
- DB를 적용할 때 도메인 객체의 변경을 최소화해야한다.

### Command

- [x] start와 end를 가진다.
      - [x] [ERROR] start와 end 이외의 값이 오면 예외 처리를 한다.
- [x] move를 가진다.
- [x] status를 가진다.
- [x] 각 명령어에 따라 뷰를 출력하고 ChessGame의 메서드를 실행한다.

## 도메인

### ChessGame

- [x] 게임을 진행한다.
- [x] Checkmate 일 경우 게임을 종료한다.

### Board

- [x] 체스판은 8행 X 8열로 총 64개의 칸을 가진다.
- [x] 흑기물의 첫 째줄은 RNBQKBNR, 둘 째줄은 PPPPPPPP로 세팅되어 있다.
- [x] 백기물의 첫 째줄은 rnbqkbnr, 둘 째줄은 pppppppp로 세팅되어 있다.
- [x] Source 위치의 Piece를 target 위치로 이동시킨다.
    - [x] [ERROR] Source 위치와 Target 위치가 같은 경우
    - [x] source 위치에 기물이 있는지 확인한다.
        - [x] [ERROR] Source 위치에 Piece가 없는 경우
    - [x] 행마법에 맞는지 확인한다.
        - [x] [ERROR] 행마법에 맞지 않는 기물을 이동할 수 없다.
    - [x] target 위치에 기물이 있는지 확인한다.
        - [x] [ERROR] 같은 Color의 기물이 있을 경우 이동할 수 없다.
    - [x] Piece가 이동하는 경로에 기물이 있는지 확인한다.
        - [x] [ERROR] 퀸, 비숍, 룩, 폰은 기물이 있을 경우 이동할 수 없다.
    - [x] target이 King인지 확인한다.
- [x] 기물이 몇개 존재하는지 계산한다.

### Score

- [x] 현재 남아있는 기물에 대한 점수를 계산한다.

### Result

- [x] 승패를 계산한다.

### Row

- [x] 행(Row)은 1~8까지 있다.
    - [x] [ERROR] 1~8이외의 값이 오면 예외 처리를 한다.
- [x] row를 증가, 감소 시킨다.
    - [x] [ERROR] 1~8을 넘어가면 예외처리를 한다.

### Column

- [x] 열(Column)은 a~h까지 있다.
    - [x] [ERROR] a~h이외의 값이 오면 예외 처리를 한다.
- [x] file을 증가 감소 시킨다.
    - [x] [ERROR] a~h를 넘어가면 예외처리를 한다.

### Position

- [x] 행과 열을 가진다.
- [x] Source와 target 위치를 Position으로 변경한다.

### Direction

- [x] 상하좌우, 대각선으로 Position을 이동한다.

### Piece

- [x] 기물의 이름을 반환한다.

### EmptyPiece

- [x] 기물이 없음을 의미한다.

### FullPiece

- [x] 기물이 존재함을 의미한다.
- [x] Color가 자신의 기물과 일치하는지 확인한다.
- [x] 이동 요청이 행마법에 맞는지 확인한다.

- [x] 킹(K), 퀸(Q), 비숍(B), 나이트(N), 룩(R), 폰(P)으로 총 6가지가 있다.
- [행마법]
    - [x] 킹은 상하좌우, 대각선 방향으로 한 칸만 이동 가능하다.
    - [x] 퀸은 상하좌우, 대각선 방향으로 기물이 없는 칸에 한해서 칸 수에 제한 없이 이동할 수 있다.
    - [x] 비숍은 대각선 방향으로 기물이 없는 칸에 한해서 칸 수에 제한 없이 이동할 수 있다.
    - [x] 나이트는 상하좌우 2칸 앞으로 이동한 후, 양 옆으로 한 칸 이동할 수 있다. 이 때 나이트는 기물을 넘을 수 있다.
    - [x] 룩은 상하좌우 방향으로 기물이 없는 칸에 한해서 칸수에 제한 없이 이동할 수 있다.
    - [x] 폰은 앞으로 한 칸만 이동이 가능한데, 앞에 기물이 있는 경우 이동할 수 없으며, 대각선에 기물이 있는 경우에만 이동이 가능하다. 처음 이동할 때, 한 칸 혹은
      두 칸 이동할 수 있다.

### Color

- [x] 흑과 백이 있다.
- [x] 반대 Color를 반환한다.

## 행마법

- 킹은 상하좌우, 대각선 방향으로 한 칸만 이동 가능하다.
- 퀸은 상하좌우, 대각선 방향으로 기물이 없는 칸에 한해서 칸 수에 제한 없이 이동할 수 있다.
- 비숍은 대각선 방향으로 기물이 없는 칸에 한해서 칸 수에 제한 없이 이동할 수 있다.
- 나이트는 상하좌우 2칸 앞으로 이동한 후, 양 옆으로 한 칸 이동할 수 있다. 이 때 나이트는 기물을 넘을 수 있다.
- 룩은 상하좌우 방향으로 기물이 없는 칸에 한해서 칸수에 제한 없이 이동할 수 있다.
- 폰은 앞으로 한 칸만 이동이 가능한데, 앞에 기물이 있는 경우 이동할 수 없으며, 대각선에 기물이 있는 경우에만 이동이 가능하다. 처음 이동할 때, 한 칸 혹은 두 칸 이동할
  수 있다.

## 페어프로그래밍 규칙

- 최대한 모든 코드를 TDD를 이용하여 작성한다.
- 불변을 지향한다.
- 각자 모르는게 있을 때, 이해가 될 때까지 토론한다.
- 도메인 용어를 최대한 사용한다.
- 도메인 커버리지를 100%로 목표로 한다.
