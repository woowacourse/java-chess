# java-chess
체스 게임 구현을 위한 저장소


## Chess
### Board
- TreeMap<Position, Square> 


## DB TABLE
- ROUND (id, 상태)
- COMMAND (id, round_id, from, to)

##말 이동의 경우의 수

- 전제: 우리팀(흰색) 적팀(검은색)이 있다고 가정한다
3. 우리팀 -> 빈칸 (이동)
4. 빈칸 -> 빈칸 (명령어 실행 안됨)
6. 말의 이동 규칙에 알맞지 않게 움직이면 이동 못한다

TODO 
1. 우리팀 -> 적팀 (공격)
2. 우리팀 -> 우리팀 (이동 불가)
5. 우리팀이 적팀의 말을 이동시킬 수 없다
