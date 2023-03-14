# 기능 구현 목록

## 출력
- [ ] 체스판 초기화 상태 출력


## 도메인
- [ ] 기물 (Piece -> 각 기물들이 상속)
  - [ ] 각 기물 타입에 맞는 description을 반환한다.
  - [x] 좌표를 가지고 있다. (Coordinate)
    - [x] x좌표 (1 ~ 8) (Row)
      - [x] [예외 처리] 1~8의 범위를 벗어난 경우 예외 처리
    - [x] y좌표 (a ~ h) (Column)
      - [x] [예외 처리] a~h의 범위를 벗어난 경우 예외 처리
  - [ ] 팀 종류를 가지고 있다. (Team enum class -> White or Black)
    - [ ] 해당 인스턴스가 White 팀인지 알려준다.
- [ ] 맵 (ChessBoard -> List<Piece>)
  - [ ] 체스판을 초기화할 때 말들이 생성된다.
- [ ] 각 기물 객체 Matcher (PieceMatcher)
  - [ ] 심볼마다 매칭되는 기물을 가지고 온다.