# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

### InputView
- [ ] 게임 시작은 start, 종료는 end를 입력한다. 

### OutputView
- [ ] 체스 기물 표시
  - [ ] 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.

### Board
- [ ] 체스 게임을 할 수 있는 체스판을 초기화한다.
- [ ] 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

### Piece
- [x] 체스 기물을 의미한다.
- [x] King(K), Queen(Q), Bishop(B), Knight(N), Rook(R), Pawn(P) 각자의 이름을 가진다.
