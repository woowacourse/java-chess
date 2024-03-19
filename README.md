# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

# 기능 구현 목록

## 체스판
- [x] 체스말과 체스판을 생성한다.
- [ ] 입력에 맞춰 말을 이동 시킨다.
  - [ ] 체크 상황인데 체크를 벗어나는 수가 아니면 예외가 발생한다.
  - [ ] 이동 경로에 막힌 곳이 있으면 하면 예외가 발생한다.
  
## 체스말
- [x] 체스말은 종류와 색을 갖고 생성한다.
- [ ] 다음 좌표가 규칙에 맞는지 확인한다.
  - [ ] 규칙에 맞지 않은 이동이면 예외가 발생한다.
### 체스말 이동 규칙
- 폰
  - 처음은 2칸 혹은 1칸 직진할 수 있다.
  - 처음 위치를 벗어난 후부터는 1칸만 직진할 수 있다.
- 나이트
  - 1칸 이동 후 대각선으로 이동할 수 있다.
  - 막힌 경로여도 뛰어넘을 수 있다.
- 비숍
  - 대각선으로 이동할 수 있다.
  - 중간에 다른 말로 막혀있으면 이동할 수 없다.
- 룩
  - 상하좌우 이동할 수 있다.
  - 중간에 다른 말로 막혀있으면 이동할 수 없다.
- 퀸
  - 상하좌우, 대각선으로 이동할 수 있다.
  - 중간에 다른 말로 막혀있으면 이동할 수 없다.
- 킹
  - 상하좌우, 대각선 1칸을 이동할 수 있다.

## 입력
- [x] 사용자는 start를 입력해 게임을 시작할 수 있다.
- [x] 사용자는 end를 입력해 게임을 종료할 수 있다.
  - [x] start / end가 아닌 입력은 예외가 발생한다.
- [ ] 사용자는 source와 target을 입력해 말을 이동시킬 수 있다.
  - [ ] 잘못된 명렁어가 들어오면 예외가 발생한다.

## 출력
- [x] 만들어진 체스판을 출력한다.