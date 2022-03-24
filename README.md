# java-chess

## 페어 프로그래밍 목표
- 판다: 처음 짤 때부터 깔끔하게 짜자.
- 라라: 페어의 순서와 역할을 존중하자. 의견을 적극적으로 제안하고 받아들이자!
- 공동: TDD 를 잘 지키려 노력하자. 

## 기능 구현 목록
- [x] 체스판을 초기화한다.
  - [x] 가로 위치는 왼쪽부터 a ~ h로 한다.
  - [x] 세로 위치는 아래부터 위로 1 ~ 8로 한다.
  - [x] 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다. 
- [x] start 를 입력받으면 게임을 초기화한다.
  - [x] 초기화된 보드를 출력한다.
- [x] 기물 이동
  - [x] `move source위치 target위치` 형식으로 입력받는다.
  - [x] 이동된 기물을 옮기고, 잡힌 기물은 제거한다.
  - [x] 이동된 결과 보드를 출력한다.
- [x] King이 잡히면 게임을 종료한다. 
- [x] status를 입력하면 각 진영의 점수를 계산한다. 
  - [x] 각 진영의 점수를 출력한다.  
  - [x] 어느 진영이 이겼는지 출력한다.
- [x] end 를 입력받으면 게임을 종료한다.

## 예외 처리 목록
- [x] `start` 또는 `end` 를 입력했는지
- [x] command 입력
  - [x] move command
    - [x] `move source target` 형식에 맞는 입력인지 
    - [x] 유효힌 좌표값인지
    - [x] source 위치에 기물이 존재하는지
    - [x] 기물에 적합한 움직임인지
    - [x] 경로상 장애물이 없는지
  - [x] `status` 형식에 맞는 입력인지

## 객체 목록
- Piece: 체스 기물
  - King
  - Queen
  - Rook
  - Bishop
  - Knight
  - Pawn 
  - Blank : 기물이 없음을 뜻하는 기물
- InitialPiece: 기물들의 초기 정보 enum
- Board: 체스판 
- Position: 기물의 위치 
- PositionX: 가로 위치 enum 
- PositionY: 세로 위치 enum 
- Color: 기물의 색깔이 저장된 enum 
- ChessGame: 체스 게임
