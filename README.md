# java-chess

체스 미션 저장소


# 기능 목록

## 기물 이동 규칙
| 왼쪽_위  | 위     | 오른쪽_위  |
|-------|-------|--------|
| 왼쪽    | *     | 오른쪽    |
| 왼쪽_아래 | 아래    | 오른쪽_아래 |

### 킹
- [x] 이동 방향은 `위`, `아래`, `왼쪽`, `오른쪽`, `왼쪽_위`, `오른쪽_위`, `왼쪽_아래`, `오른쪽_아래`이다.
- [x] 1칸씩 이동한다.
- [x] 이동할 칸에 같은 색 기물이 있다면 이동할 수 없다.

### 퀸
- [ ] 이동 방향은 `위`, `아래`, `왼쪽`, `오른쪽`, `왼쪽_위`, `오른쪽_위`, `왼쪽_아래`, `오른쪽_아래`이다.
- [ ] n칸씩 이동한다.
- [ ] 이동 칸에 같은 색 기물이 있다면 이동할 수 없다.
- [ ] 이동할 방향에 기물이 있다면 해당 기물 전 칸까지만 이동할 수 있다.

### 비숍
- [x] 이동 방향은 `왼쪽_위`, `오른쪽_위`, `왼쪽_아래`, `오른쪽_아래`이다.
- [x] n칸씩 이동한다.
- [x] 이동할 방향에 같은 색 기물이 있다면 해당 기물 전 칸까지만 이동할 수 있다.
- [x] 이동할 방향에 같은 다른 색 기물이 있다면 해당 기물 칸까지만 이동할 수 있다.

### 나이트
- [x] 이동 방향은 `위 -> 오른쪽_위`, `위 -> 왼쪽_위`, `아래 -> 오른쪽_아래`, `아래 -> 왼쪽_아래`,
      `왼쪽 -> 왼쪽_위`, `왼쪽 -> 왼쪽_아래`, `오른쪽 -> 오른쪽_아래`, `오른쪽 -> 오른쪽_위`이다.
- [x] 1칸씩 이동한다.
- [x] 이동할 칸에 같은 색 기물이 있다면 이동할 수 없다.
- [x] 이동할 방향에 기물이 있어도 이동할 수 있다.

### 룩
- [ ] 이동 방향은 `위`, `아래`, `왼쪽`, `오른쪽`이다.
- [ ] n칸씩 이동한다.
- [ ] 이동할 칸에 같은 색 기물이 있다면 이동할 수 없다.
- [ ] 이동할 방향에 기물이 있다면 해당 기물 전 칸까지만 이동할 수 있다.

### 폰
- [ ] 흑의 이동 방향
  - [ ] rank7에 위치하면 이동은 `아래 -> 아래`, `아래`이다.
  - [ ] rank7에 위치하지 않는다면 이동은 `아래`이다.
  - [ ] `왼쪽_아래`, `오른쪽_아래`에 백 기물이 존재하면 이동은 `왼쪽_아래`, `오른쪽_아래`다.

- [ ] 백의 이동 방향
  - [ ] rank2에 위치하면 이동은 `위 -> 위`, `위`이다.
  - [ ] rank2에 위치하지 않는다면 이동은 `위`다.
  - [ ] `왼쪽_위`, `오른쪽_위`에 흑 기물이 존재하면 이동은 `왼쪽_위`, `오른쪽_위`다.

### 입력과 출력
- [x] 아래와 같이 시작 메시지를 출력한다.
```
> 체스 게임을 시작합니다.
> 게임 시작 : start
> 게임 종료 : end
> 게임 이동 : move source위치 target위치 - 예. move b2 b3
```
- [x] 게임 시작 여부를 입력 받는다.
  - [x] 입력이 start, end가 아닌 경우 예외가 발생한다.
  - [x] start를 입력 받으면 게임이 시작된다.
  - [x] end를 입력 받으면 게임이 종료된다.
- [x] 이동 명령어를 입력 받는다.
  - [ ] 형식이 `move A B`가 아니면 예외가 발생한다.
  - [ ] rank가 `1-8`사이의 범위가 아니면 예외가 발생한다.
  - [ ] file이 `a-h`사이의 범위가 아니면 예외가 발생한다.
  - [ ] 흑백을 번갈아 이동시킨다.
    - [ ] 백의 선공으로 게임을 시작한다.
    - [ ] 바로 앞에서 움직인 기물의 색과 지금 움직이려는 기물의 색이 동일하다면 예외가 발생한다.
    - [ ] 기물이 이동할 수 없는 경우 예외가 발생한다.
- [ ] 예외가 발생하면 다시 입력 받는다.

- [x] 보드판을 출력한다.
  - [x] 흑은 대문자로, 백은 소문자로 출력한다.
  - [x] 빈 칸은 `.`으로 출력한다.
  - [x] 킹, 퀸, 룩, 비숍, 나이트, 폰을 각각 `K`, `Q`, `R`, `B`, `N`, `P`로 출력한다.

### 체스판
- [x] 초기 기물 위치를 초기화 한다.
  - [x] 흑은 rank7과 rank8에 위치한다.
  - [x] 백은 rank2와 rank1에 위치한다.
  - [x] 초기 기물의 위치는 아래와 같다.
```    
    RNBQKBNR
    PPPPPPPP
    ........
    ........
    ........
    ........
    pppppppp
    rnbqkbnr
```

- 체크 또는 체크메이트는 고려하지 않고 이동한다.

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
