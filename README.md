# java-chess
체스 게임 구현을 위한 저장소
---

## 1-2 단계
---

### 말 객체 생성하기

- 각각의 말은 이동 규칙에 대한 메소드를 갖는다.  

- 이동 규칙

```markdown
- Rook : 앞, 뒤, 양 옆으로만 원하는 만큼 이동 가능
- Bishop : 대각선으로만 원하는 만큼 이동 가능
- Knight : 한 방향으로 두 칸 이동 후 옆으로 한 칸 이동 / 유일하게 다른 기물을 뛰어넘을 수 있음
- Pawn : 일반 이동은 앞으로 한 칸 / 잡는 이동은 대각선 한 칸
- King : 위, 아래, 양 옆, 대각선 모두 한 칸씩 이동 가능 
- Queen : 일직선으로 위, 아래, 양 옆, 대각선 모두 원하는 만큼 이동 가능
```

- Rule check : 해당 말이 한 번에 움직일 수 있는 위치인지 Point 간의 비교를 통해 확인한다.


### Point 객체 생성하기

- String 문자열을 받아서 x좌표와 y좌표을 구한다.  
- 생성 시에 x, y를 1 ~ 8 사이의 숫자로 변환한다.  
- static factory method로 Point를 생성하고, static Map(혹은 Pool)의 형태로 Point를 캐싱한다.  
- Point에서 RelativePoint를 받아서 연산하는 메소드  

- 예외 처리 

```markdown
- 범위를 벗어난 경우 예외처리
```


### RelativePoint 객체 생성하기

- unit direction을 구하는 메소드


### Player 객체 생성하기

- 각 팀의 현재 살아있는 말들을 가지고 있다.  
- 해당 Point에 말을 갖고 있는지 조회하는 메소드  
- 현재 Point와 목적 Point를 받아 말을 이동하는 메소드  
	1. Rule check : 해당 말에게 특정 Point 위치로 움직일 수 있는지 물어본다.  
	2. 말에게서 방향 정보인 RelativePoint를 얻어온다.  
	3. 해당 Point가 Destination에 다다르기 직전까지
		- Point에 RelativePoint를 더한다.
		- 아군, 적군 말이 있는지 각각 확인한다. 있으면 FAIL.
	4. Destination에서
		- 아군일 경우 FAIL
		- 적군일 경우 SUCCESS. 적군의 해당 Point의 말을 제거한다. 
- 해당 Point의 말을 제거하는 메소드  
- King 을 확인하는 메소드
- 현재 상황의 점수를 계산하는 메소드
	- pawn의 경우 다른 pawn들의 위치까지 고려하여 계산  


### 체스 게임을 의미하는 Round 객체

- black팀과 white팀을 가질 수 있다.  
- 현재 차례의 플레이어가 누군지 기억할 수 있어야 한다.  
- move
	- source에 해당 팀의 말이 있는지 체크
	- target에 해당 팀의 말이 없는지 체크
	- Player 안에 있는 말 이동 로직을 호출
	- 차례를 상대방에게 넘김  

- 게임 진행을 끝내는 메소드  


### 체스 말을 초기화하는 ChessPiecesBuilder

- 게임이 시작되면 초기화된 말들을 생성하여 Map의 형태로 반환한다.
- DB에서 가져온 진행중인 게임도 Map의 형태로 만들어서 반환해준다.  



