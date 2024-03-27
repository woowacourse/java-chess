# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)


# 객체 협력 관계
![object-relations](src/main/java/resources/static/chess-step-1.png)

# 기능 요구사항(step 1&2)

## Board
- [x] 초기의 체스판에 기물을 적절한 위치와 컬러에 맞게 세팅한다.
- [x] 민들어진 체스판을 보여준다.
- [x] 플레이어의 색을 받아서 해당 색의 기물을 움직인다.
  - ⛔️️[x] 플레이어의 색과 움직이는 기물의 색이 다를 경우 예외가 발생한다.
- [x] 이동위치를 받아 기물을 움직이다.
- [x] 상대방의 기물이 있는 위치에 이동하면, 현재 기물로 덮어쓴다.

## Piece
- [x] 폰, 킹, 나이트, 퀸 등등으로 이루어져 있다.
- [x] 해당 위치로 이동할 수 있는지 판별한다.
- [x] true면 해당 위치로 이동할 수 있다.
- 폰 움직임
  - [x] 시작위치에서는 2칸 전진할 수 있다.
  - [x] 1칸 전진할 수 있다
  - [x] 상대 기물이 있을 경우 대각선으로 공격할 수 있다
  - [x] 시작위치가 아닌 경우 2칸 이동할 수 없다
  - [x] 상대 기물이 없을 경우 공격할 수 없다
  - [x] 상대 기물이 바로 앞에 있을 경우 공격할 수 없다

## Game
- [x] 사용자에게 Start를 전달받으면 게임을 시작한다.
- [x] 사용자에게 End를 전달받으면 게임을 종료한다.
- [x] 사용자에게 {move source위치 target위치}를 입력받는다. 
  - [x] WHITE와 BLACK 순으로 돌아가면서 입력받는다.
  - ⛔️️[x] 입력형식은 `move b2 b3`와 같은 형식으로 입력되지 않으면 예외가 발생한다.

## Square
- [x] Rank를 가지고 있다.
- [x] File을 가지고 있다.
  - ⛔️️[x] 이동 위치가 유효하지 않으면 예외가 발생한다.

# 기능 요구사항(step 3&4)

## Piece
- [x] queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.
- [x] pawn의 기본 점수는 1점이다.
- [x] 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.
- [x] king은 0점이다.

## Game
- [x] King이 잡혔을 때 게임을 종료해야 한다.
- [x] 남아 있는 말에 대한 점수를 구한다.
- [x] 재입력을 구현한다.
- [x] Pawn 전진 공격 시 수정한다.

## Application
- [x] 애플리케이션을 재시작하더라도 이전에 하던 체스 게임을 다시 시작할 수 있어야 한다.
- [x] DB를 적용할 때 도메인 객체의 변경을 최소화해야한다.
- [x] 체스 게임방을 만들고 체스 게임방에 입장할 수 있는 기능을 추가한다.
  - [x] 사용자를 생성할 수 있다.
  - [x] 모든 사용자를 조회할 수 있다.
- [x] 사용자별로 체스 게임 기록을 관리할 수 있다.
 - [x] 게임을 기록한다.
- [ ] 게임 종료 시, 점수를 출력한다.
- [ ] 턴이 바뀔 때마다 출력한다.
