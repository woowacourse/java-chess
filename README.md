# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

### 프로그램 동작 순서

1. 체스 게임 시작 문구를 출력한다.
2. 사용자는 start 명령을 입력한다.
3. 초기화된 체스판을 출력한다.
4. 사용자는 end 명령을 입력한다.

### 기능 목록 구현
- [ ] 위치(Position)
    - [ ] file(열)과 rank(행)를 갖는다
- [ ] 체스말(Piece)
    - [ ] 이름을 갖는다
    - [ ] 체스말은 Rook, Knight, Bishop, King, Queen, Pawn을 갖는다
- [ ] 체스판(Board)
    - [ ] 위치와 체스말을 갖는다
- [ ] 체스판 청사진
    - [ ] 체스판을 빈 공간으로 채운다
    - [ ] 체스판에 검은말을 채운다
    - [ ] 체스판에 흰말을 채운다
- [ ] 뷰
    - [ ] 입력(InputView)
        - [ ] 사용자는 명령어를 입력한다(start, end)
    - [ ] 출력(OutputView)
        - [ ] 초기화된 체스판을 출력한다
