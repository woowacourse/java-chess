# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 기능 구현 목록

### 입력
- [ ] start 입력 시 게임을 시작한다.
- [ ] end 입력 시 게임을 종료한다.

### 출력
- [ ] 판을 출력한다.
    - [ ] 가로는 왼쪽부터 a-h, 세로는 아래부터 1-8 이다.
    - [ ] 검은 말은 대문자로, 흰 말은 소문자로 출력한다.

### board
- [ ] piece들을 가진다.


### piece
- [ ] 위치를 가진다.
- [ ] 팀을 가진다.

### position
- [x] 이차원 위치 값을 가지고 있는다.
  - [x] 1-8 사이의 값이어야한다.

### team(enum)
- [ ] BLACK, WHITE

### pawn
- [ ] piece를 상속한다.

### knight
- [ ] piece를 상속한다.

### bishop
- [ ] piece를 상속한다.

### rook
- [ ] piece를 상속한다.

### queen
- [ ] piece를 상속한다.

### king
- [ ] piece를 상속한다.


### 출력 예시

```
체스 게임을 시작합니다.
게임 시작은 start, 종료는 end 명령을 입력하세요.
start
RNBQKBNR
PPPPPPPP
........
........
........
........
pppppppp
rnbqkbnr

end
```