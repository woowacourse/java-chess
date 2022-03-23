# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 체스 기능 요구사항

### 1단계

- [x] 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.
- [x] 말은 종류와 색으로 구성되어 있다.
- [x] 보드에서 말들을 초기 위치로 생성한다.
- [x] 검정말은 대문자, 흰색말은 소문자로 표현한다.
    - [x] 말이 없는 칸은 .으로 표현한다.
- [x] 게임 시작은 start, 종료는 end 명령이다.
    - [x] start, end가 아닌 입력은 예외 처리한다.

### 2단계

- [x] source 위치에 말이 없으면 예외를 발생시킨다.
- [ ] source 와 target은 같을 수 없다.
- [ ] 폰은 직진으로 한칸에서 두 칸까지 이동할 수 있다.
    - [ ] 위쪽 좌우 대각선에 상대 기물이 있으면 이동할 수 있다.
    - [ ] 두 칸은 처음 움직일 때만 해당한다.
- [ ] 나이트는 상하좌우 두칸 직진 후 좌우로 이동할 수 있다.
- [ ] 룩은 상하좌우로 칸 제한 없이 직진할 수 있다.
- [ ] 비숍은 상하 대각선으로 칸 제한 없이 이동할 수 있다.
- [ ] 퀸은 룩과 비숍의 이동방법을 모두 갖는다.
- [ ] 킹은 상하좌우 한칸, 위 아래 대각선 한칸씩 움직일 수 있다.
- [ ] 이동하려는 경로에 (목적지 제외) 다른 기물이 있으면 목적지로 이동할 수 없다.
    - [ ] 나이트는 해당 안됨

