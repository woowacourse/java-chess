# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)


## 기능 목록

### 입력
- [x] 게임 시작 또는 종료 명령을 입력받는다(start, end)
- [x] 체스 말의 이동 정보(move source, target)를 입력받는다.
  - [x] 체스판의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8임을 검증한다.

### 출력
- [x] 게임 안내 문구를 출력한다.
- [x] 체스판을 현 상황을 출력한다.
  - [x] 검은색 말은 대문자, 흰색 말은 소문자로 구분한다.

### ChessGame

### Board
- [x] 초기화 기능
  - [x] 체스말 놓기
- [x] 말을 움직이는 기능
  - [x] 움직이려는 자리에 말이 있는지 확인
  - [x] 말이 있다면 갈 수 있는 위치인지 확인
  - [x] 갈 수 있다면 그 사이에 방해물이 없는지 확인
  - [x] 방해물이 없다면 말 이동
- [x] 정렬된 보드를 반환

### Piece

- [X] 말은 Pawn, Bishop, Knight, Rook, Queen, King 으로 구성
- [X] 말은 흑 또는 백
- [X] 좌표를 입력받아서 움직일 수 있는지 확인

### Movement

- [X] 상대 위치까지의 이동이 가능한지 판별

### RelativePosition

- [x] 상대 위치를 단위 상대 위치로 변환하는 기능

### Direction

- [x] 말의 최소 이동 단위 제공

### Position

- [x] 입력된 위치가 유효한지 검증
- 
