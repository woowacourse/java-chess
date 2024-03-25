# java-chess

체스 미션 저장소

# 요구사항

## 1단계 요구사항

- 콘솔 UI에서 체스 게임을 할 수 있는 기능을 구현한다.
- 1단계는 체스 게임을 할 수 있는 체스판을 초기화한다.
- 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

### 요구사항 해석

- 킹, 퀸, 룩, 비숍, 나이트, 폰을 각각 `K`, `Q`, `R`, `B`, `N`, `P`로 출력한다.
- 빈 칸은 `.`으로 출력한다.

## 2단계 요구사항

- 체스 말의 이동 규칙을 찾아보고 체스 말이 이동할 수 있도록 구현한다.
- move source위치 target위치을 실행해 이동한다.
- 체스판의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

### 요구사항 해석

- 기물이 이동할 수 없는 경우 예외가 발생한다. 
- 백과 흑은 차례를 번갈아가며 기물을 이동한다. 
  - 백의 선공으로 게임을 시작한다. 
- 다른 기물이 있는 위치로는 이동할 수 없다. 
- 상대의 기물을 공격할 수 있다. 
- 기물은 빈칸으로 또는 상대 기물이 있는 칸으로 공격하며 이동할 수 있다.
  - 폰은 앞으로만 이동할 수 있다. 
  - 상대 기물이 있는 칸으로 이동할 경우 상대 기물을 공격해 없앤다. 
    - 폰은 대각선 앞에 상대 기물이 있는 경우에만 공격하고 이동할 수 있다. 
  - 나이트를 제외한 모든 기물은 다른 기물을 건너뛸 수 없다. 
- 체크를 당하는 위치로 기물을 이동할 수 없다.
- 이동 후 체크메이트 또는 스테일메이트인 경우 게임을 종료한다.  
- 특수 규칙
  - 캐슬링
  - 앙파상
  - 프로모션

## 프로그램 실행 결과

```
> 체스 게임을 시작합니다.
> 게임 시작 : start
> 게임 종료 : end
> 게임 이동 : move source위치 target위치 - 예. move b2 b3
start
RNBQKBNR
PPPPPPPP
........
........
........
........
pppppppp
rnbqkbnr

move b2 b3
RNBQKBNR
PPPPPPPP
........
........
........
.p......
p.pppppp
rnbqkbnr
```

# 기능 목록

- [x] 체스 게임 시작 메시지를 출력한다.
  - [x] 게임 시작, 종료, 이동 명령어 안내 메시지를 출력한다. 
- [x] 게임 시작 여부를 입력받는다.
  - [x] 입력이 start, end가 아닌 경우 예외가 발생한다.
- [x] 모든 체스 기물을 생성한다.
- [x] 체스판 위에 기물을 배치한다.
- [x] 체스판을 출력한다. 
  - [x] 흑은 대문자로, 백은 소문자로 출력한다. 
- [ ] 백과 흑은 차례를 번갈아가며 기물을 이동한다.
- [x] 이동 명령어를 입력받는다. 
  - [x] 명령어에 의해 체스판에서 기물을 이동한다.
    - [ ] 상대 기물이 있는 칸으로 공격하며 이동할 수 있다.
    - [x] 킹은 모든 방향으로 한 칸 움직일 수 있다. 
    - [x] 퀸은 수평, 수직 및 대각선으로 원하는 만큼 움직일 수 있다.
    - [x] 비숍은 대각선으로 원하는 만큼 움직일 수 있다. 
    - [x] 나이트는 `L` 모양, 즉 수평으로 두 칸 이동하고 수직으로 한 칸 움직일 수 있다.
    - [x] 룩은 수평, 수직으로 원하는 만큼 이동할 수 있다.
    - [x] 폰은 앞으로만 이동할 수 있다. 
      - [x] 대각선 앞에 상대 기물이 있는 경우에는 상대 기물을 공격하고 해당 자리로 이동할 수 있다. 
  - [x] `end`를 입력할 경우 프로그램을 종료한다. 
- [ ] 잘못된 명령어를 입력한 경우 에러 메시지를 출력하고 다시 입력받는다. 
