## 도메인 정의
- 체스 게임
- 체스 말: Piece
- 위치 값
  - ex. (1, a)
- 체스판
- 플레이어
- 점수

## 객체 협력 정리
![object_cooperation](https://user-images.githubusercontent.com/50176238/111277975-6300de00-867c-11eb-9175-601b2b382d33.png)

## 1단계 기능목록 정리
- [x] 게임 실행 메시지를 출력한다.
- [x] Piece 객체 생성
  - [x] 객체 생성 시 위치를 초기화한다.
  - [x] 옳지 않은 기물인 경우에 예외를 발생한다.
- [x] Position 객체 생성
  - [x] rank가 1~8이 아닌 경우에 예외를 발생한다.
  - [x] file이 a~h이 아닌 경우에 예외를 발생한다.
- [x] ChessBoard 객체 생성
  - [x] ChessBoardFactory를 생성한다. 
    - [x] 유효한(1-8, a-h)의 범위가 아니면 에러가 발생한다.
  - [x] PieceFactory를 생성한다.
- [x] start를 입력하면 체스판이 출력된다. 
- [x] 체스판을 출력한다.
- [x] end를 입력하면 프로그램이 종료된다.

<br>

## 2단계 기능목록 정리
- [x] 게임 실행 메시지를 출력한다.
- [x] start를 입력하면 체스판이 출력된다.
- [x] 체스판을 출력한다.
- [x] `move source target`을 실행해 이동한다. 
  - [x] ex. `move b2 b3`
    - [x] move가 아닌 다른 명령어가 들어오면 예외가 발생한다.
    - [x] source의 위치가 없는 위치면 예외가 발생한다.
    - [x] source 위치에 말이 없으면 예외가 발생한다. 
    - [x] source로 선택된 말이, 올바른 턴이 아니면, 예외가 발생한다. 
      - ex. `하얀색 턴이면 소문자 기물, 검은색 턴이면 대문자 기물`을 선택해야한다.
    - [x] source와 target이 동일한 경우 예외가 발생한다.
    - [x] target의 위치가 없는 위치면 예외가 발생한다.
    - [x] source에서 선택한 말과 target의 말이 같은 색깔이면 예외가 발생한다.
    - [x] source에서 선택한 말이 움직임 수 없는 위치를 target에서 선택하면 예외가 발생한다. 
      - ex. 원래는 지나갈 수 있어도, 이동 경로에 다른 말이 있는경우 지나갈 수 없다.

### Rook 테스트 케이스 예시
![rook](https://user-images.githubusercontent.com/50176238/111596208-40073300-8810-11eb-904e-feb0c43117fa.jpg)

### King 테스트 케이스 예시
![king](https://user-images.githubusercontent.com/50176238/111624064-8c616b80-882e-11eb-919f-e7c162aa22a1.jpg)

### Bishop 테스트 케이스 예시
![bishop](https://user-images.githubusercontent.com/48986787/111715251-c8c8b200-8896-11eb-801d-1c08328b3353.png)

### Queen 테스트 케이스 예시
![queen](https://user-images.githubusercontent.com/50176238/111739494-7b643900-88c6-11eb-9f0b-b981f2ce3730.jpg)

### Knight 테스트 케이스 예시
![knight](https://user-images.githubusercontent.com/50176238/111741570-0692fe00-88ca-11eb-8e6c-e9ff71db6e84.jpg)

### Pawn 테스트 케이스 예시
![pawn](https://user-images.githubusercontent.com/48986787/111746634-a6a05580-88d1-11eb-8f12-bdcfc8b166ba.png)

<br>

## 3단계 기능목록 정리
- [x] King이 잡혔을 때 게임을 종료한다.
- [x] 현재 남아 있는 말에 대한 점수를 구한다.
  - [x] Queen: 9점, Rook: 5점, Bishop: 3점, Knight: 2.5점
  - [x] Pawn: 기본 1점, 같은 세로 줄에 같은 색의 Pawn이 있는 경우 0.5점
  - [x] 한 번에 한 쪽의 점수만을 계산한다.
    - ex. white 턴이면 white 진영의 점수, black 턴이면 black 진영의 점수를 계산한다.
- [ ] `status` 명령을 입력하면 각 진영의 점수를 출력하고, 어느 진영이 이겼는지 결과를 보여준다.
