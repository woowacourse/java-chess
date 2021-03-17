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
- [ ] 게임 실행 메시지를 출력한다.
- [x] Piece 객체 생성
  - [x] 객체 생성 시 위치를 초기화한다.
  - [x] 옳지 않은 기물인 경우에 예외를 발생한다.
- [x] Position 객체 생성
  - [x] rank가 1~8이 아닌 경우에 예외를 발생한다.
  - [x] file이 a~h이 아닌 경우에 예외를 발생한다.
- [ ] ChessBoard 객체 생성
  - [x] ChessBoardFactory를 생성한다. 
    - [x] 유효한(1~8, a~h)의 범위가 아니면 에러가 발생한다.
  - [x] PieceFactory를 생성한다.
- [ ] start를 입력하면 체스판이 출력된다. 
- [ ] 체스판을 출력한다.
- [ ] end를 입력하면 프로그램이 종료된다.