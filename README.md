# java-chess
체스 게임 구현을 위한 저장소

# 기능 요구사항

### 좌표
- [x] 좌표는 행과 열 enum으로 이루어져 있다.

### 기물
- [x] 이동 룰을 가진다.
- [x] 흑백을 가진다.
- [x] 기물의 종류는 폰, 룩, 나이트, 비숍, 퀸, 킹 이 있다.

### 체스 보드
- [x] 기물과 위치를 가진다.
- [x] 기물 초기 위치를 초기화한다.
- [x] 체스판의 위치 값은 가로위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

### 기물 이동
- 기물이동 위치에 기물이 있을 경우
  - [x] 상대 기물일 경우 잡는다.
  - [x] 자신의 기물일 경우 이동할 수 없다.
- [x] 해당 기물의 이동룰에 따라 이동한다.
- [x] 보드 바깥으로 이동할 수 없다.
- [x] 이동하는 경로에 다른 기물이 있으면 이동할 수 없다.

### 결과
- [x] King이 잡혔을 경우 패배
- [x] status 명령 입력시 진영 점수와 결과를 출력한다.
- 점수 계산 규칙
    - [x] queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.
      pawn의 기본 점수는 1점이다. 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.
    - [x] King은 점수가 없다.
    - [x] 한 번에 한 쪽의 점수만을 계산해야 한다.
    
### 체스 게임
- [x] 게임 시작은 start, 종료는 end 명령을 받는다.
- [x] 기물 이동은 move from위치 to위치을 입력 이동한다.

# 체스 웹 UI 적용
- [x] 메인화면에서 기본 체스판을 출력해야한다.
- [ ] 기물을 마우스로 움직일 수 있어야한다.
  - [ ] 기물을 선택하면 표시되어야한다.
  - [ ] 이동 룰에 의해 움직여야한다.
- [ ] 선택한 기물을 다시 선택하면 선택이 취소되어야 한다.
- [ ] 현재 턴을 표시해주어야한다.
