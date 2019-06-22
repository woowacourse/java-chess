# java-chess
체스 게임 구현을 위한 저장소

## Chess
### Board
- TreeMap<Position, Square> 

## DB TABLE
- ROUND (id, 상태)
- COMMAND (id, round_id, target, origin)

##말 이동의 경우의 수

- 전제: 우리팀(흰색) 적팀(검은색)이 있다고 가정한다
1. 우리팀 -> 빈칸 (이동)
2. 빈칸 -> 빈칸 (명령어 실행 안됨)
3. 말들(룩, 퀸, 비숍, 폰)이 이동할 때 중간에 장애물이 있을 경우 이동시킬 수 없다
4. 말의 이동 규칙에 알맞지 않게 움직이면 이동 못한다
5. 우리팀 -> 적팀 (공격)
6. 우리팀 -> 우리팀 (이동 불가)
7. 우리팀이 적팀의 말을 이동시킬 수 없다
8. 킹이 잡혔을 때 게임을 종료한다


## 말 이동
### 폰
- 컬러에 따라서 이동 경우의 수가 달라진다.

- 첫번째 폰 움직임 후 두번째 폰으로 바꿔주기